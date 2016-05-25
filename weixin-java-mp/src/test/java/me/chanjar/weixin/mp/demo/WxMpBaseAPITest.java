package me.chanjar.weixin.mp.demo;

import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric Shang on 2015/7/21.
 */
public class WxMpBaseAPITest {
    private static final Logger logger = LoggerFactory.getLogger(WxMpBaseAPITest.class);

    @Test(singleThreaded = true)
    public void compelDeviceId() throws WxErrorException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("device_id", "gh_6b1e5bc806d4_d618be313d2dc840c38faafba8f5baa1");
        paramMap.put("openid", "ov9f-tqapxQ5ssbfAh2b-6MzwEcs");

        String jsonContent = JSONObject.fromObject(paramMap).toString();
        logger.info("jsonContent = {}", jsonContent);
        WxMpService wxMpService = new WxMpServiceImpl();
        //G100 Device
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId("wx4107f20c1747f587");
        configStorage.setSecret("c80810ceba01958a9f005bc5c49e437c");
        configStorage.setToken("microSocket");
        wxMpService.setWxMpConfigStorage(configStorage);


        String responseContent = wxMpService.compelDeviceUnbind(jsonContent);
        logger.info("responseContent = {}", responseContent);
        JsonElement tmpJsonElement = Streams.parse(new JsonReader(new StringReader(responseContent)));
        String response = tmpJsonElement.getAsJsonObject().get("base_resp").toString();
        logger.info("response = {}", response);
    }

}
