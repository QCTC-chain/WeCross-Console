package com.webank.wecross.console.custom;

import com.webank.wecross.console.common.ConsoleUtils;
import com.webank.wecross.console.common.FileUtils;
import com.webank.wecross.console.common.HelpInfo;
import com.webank.wecross.console.common.PrintUtils;
import com.webank.wecross.console.exception.ErrorCode;
import com.webank.wecross.console.exception.WeCrossConsoleException;
import com.webank.wecrosssdk.rpc.WeCrossRPC;
import com.webank.wecrosssdk.rpc.common.ResourceDetail;
import com.webank.wecrosssdk.rpc.methods.response.CommandResponse;
import com.webank.wecrosssdk.rpc.methods.response.ResourceResponse;
import com.webank.wecrosssdk.utils.RPCUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class ChainMakerCommand {
    private static final Logger logger = LoggerFactory.getLogger(ChainMakerCommand.class);
    private WeCrossRPC weCrossRPC;

    public ChainMakerCommand(WeCrossRPC weCrossRPC) {
        this.weCrossRPC = weCrossRPC;
    }

    /**
     * deploy contract
     *
     * @params ChainMakerDeploy [path] [filePath] [className] [version]
     */
    public void deploy(String[] params) throws Exception {
        if (params.length == 1) {
            throw new WeCrossConsoleException(ErrorCode.PARAM_MISSING, "chainMakerDeploy");
        }

        if ("-h".equals(params[1]) || "--help".equals(params[1])) {
            HelpInfo.ChainMakerDeployHelp();
            return;
        }

        if (params.length < 4) {
            throw new WeCrossConsoleException(ErrorCode.PARAM_MISSING, "chainMakerDeploy");
        }

        String path = params[1];
        String chain = path.substring(0, path.lastIndexOf('.') + 1);
        RPCUtils.checkPath(path);
        String stubType = "";
        ResourceResponse resources = weCrossRPC.listResources(false).send();
        for (ResourceDetail resourceDetail : resources.getResources().getResourceDetails()) {
            if (resourceDetail.getPath().startsWith(chain)) {
                stubType = resourceDetail.getStubType();
                break;
            }
        }
        if (stubType.equals("")) {
            throw new WeCrossConsoleException(ErrorCode.INVALID_PATH, path);
        }

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        List<Object> args = new ArrayList<>();

        String cnsName = path.split("\\.")[2];
        String sourcePath = params[2];
        String contractName = params[3];

        org.springframework.core.io.Resource resource = resolver.getResource("file:" + sourcePath);
        if (!resource.exists()) {
            resource = resolver.getResource("classpath:" + sourcePath);
            if (!resource.exists()) {
                logger.error("Source file: {} not exists", sourcePath);
                throw new Exception("Source file: " + sourcePath + " not exists");
            }
        }

        String filename = resource.getFilename();
        String binFileName = filename.split("\\.")[0] + ".abi";
        String realPath = resource.getFile().getAbsolutePath();
        String dir = realPath.substring(0, realPath.lastIndexOf(File.separator)) + File.separator;

        String sourceContent = FileUtils.mergeSource(dir, filename, resolver, new HashSet<>());
        String abiContent = FileUtils.readFileContent(dir + File.separator + binFileName);
        String version = params[4];
        args.addAll(Arrays.asList(cnsName, sourceContent, abiContent, contractName, version));
        for (int i = 5; i < params.length; i++) {
            // for constructor
            args.add(ConsoleUtils.parseString(params[i]));
        }

        CommandResponse response =
                weCrossRPC
                        .customCommand("DEPLOY_CHAINMAEKER_CONTRACT", path, args.toArray())
                        .send();
        PrintUtils.printCommandResponse(response);
    }
}
