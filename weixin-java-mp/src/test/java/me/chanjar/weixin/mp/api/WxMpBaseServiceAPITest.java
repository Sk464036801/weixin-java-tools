package me.chanjar.weixin.mp.api;

import com.google.gson.JsonParser;
import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.nodes.CollectionNode;

import java.io.File;


/**
 * 基础API测试
 * @author chanjarster
 *
 */
@Test(groups="baseServiceAPI", dependsOnGroups="baseAPI")
@Guice(modules = ApiTestModule.class)
public class WxMpBaseServiceAPITest {

  private static final Logger logger = LoggerFactory.getLogger(WxMpBaseServiceAPITest.class);

  @Inject
  protected WxMpServiceImpl wxService;

  public void testSendMusicMessage() throws WxErrorException {

    WxMpCustomMessage reply = new WxMpCustomMessage();
    reply.setToUser("oekW0jsW7wkn_eTR4bh3vxg9-6OA");
    reply.setMsgType(WxConsts.CUSTOM_MSG_MUSIC);
    reply.setThumbMediaId("");
    reply.setDescription("Music Description");
    reply.setTitle("Music Title");
    reply.setMusicUrl("http://zhangmenshiting.baidu.com/data2/music/64380827/64380827.mp3?xcode=07392b8ef006917fd4776cac32c72599eaa48b2ded70843d&mid=0.99714766965394");
    reply.setHqMusicUrl("http://zhangmenshiting2.baidu.com/data2/music/121238147/121238147.mp3?xcode=07392b8ef006917f34c35c34efed96459708ae1bfbfaa8e9&mid=0.99714766965394");

    logger.info("music reply = " + reply.toJson());

//    wxService.customMessageSend(reply);
  }

  public void testSendVideoMessage() throws WxErrorException {
    WxMpCustomMessage reply = new WxMpCustomMessage();
    reply.setToUser("oekW0jsW7wkn_eTR4bh3vxg9-6OA");
    reply.setMsgType(WxConsts.CUSTOM_MSG_VIDEO);
    reply.setMediaId("ul-1j36fMZbGm5R06oRJSIzEXRG7iLP22Gs1s0RkPH3cqDdoRO4P7WuFHnzpQbSl");
    reply.setThumbMediaId("");
    reply.setTitle("TITLE Demo");
    reply.setDescription("DESCRIPTION Demo");

    logger.info("reply = " + reply.toJson());

    //wxService.customMessageSend(reply);
  }

  public void testSendVoiceMessage() throws WxErrorException {

    WxMpCustomMessage reply = new WxMpCustomMessage();
    reply.setToUser("oekW0jro5gzFSZAXfhCkLFEQT6cA");
    reply.setMsgType(WxConsts.CUSTOM_MSG_VOICE);
    reply.setMediaId("HNrg9IVwznYhTQscfqqXehhVpIQL5LgYEKn60XTRIucvRjwFAsbwflUp_N_9Lyu4");

    wxService.customMessageSend(reply);

    logger.info(" reply = " + reply.toJson());
  }

  public void testSendImageMessage() throws WxErrorException {

    WxMpCustomMessage reply = new WxMpCustomMessage();
    reply.setToUser("oekW0jsW7wkn_eTR4bh3vxg9-6OA");
    reply.setMsgType(WxConsts.CUSTOM_MSG_IMAGE);
    reply.setMediaId("9jAukEyqgYsTrlPPXVG93e6L2iHpiANHiYNyh8GzOrL0vd9hMgE_yp3BW1KaIEt7");

    //wxService.customMessageSend(reply);

  }

  public void testDownloadWXResource() {
    String media_id = "NVEKhsaTYUOav_XKCMcg4bvtXwPdDUzIl5WX_Bdy0516JgVTfTrCw-_73-sf7BR0";

    File file = null;

    try {
//      file = wxService.mediaDownload(media_id);
    } catch (Exception e) {
      logger.error(" download ERROR:",e);
    }

    if(file != null) {
      logger.info("download file path = " + file.getPath());
    }else {
      logger.info("download file fail ...");
    }
  }

  public void templateSend() {
    String openId = "oekW0jsW7wkn_eTR4bh3vxg9-6OA";
    String content = "{'data':{'first':{'color':'#173177','value':'[#deviceSN#]'},'keyword1':{'color':'#173177','value':'任务已执行'},'keyword2':{'color':'#173177','value':'[#datetime#]'},'remark':{'color':'#173177','value':'设备设定计划执行提醒。'}},'template_id':'5BzoJXwhs4lERGj8kv5KJCGCKb_6q2MjxS9IpkNA3Jc','topcolor':'#173177','touser':'[#toUser#]','url':''}";
    content = content.replace("[#deviceSN#]", "SN001")
            .replace("[#userNo#]", "xiaoshangXX")
            .replace("[#toUser#]", openId)
            .replace("[#datetime#]", "2015-08-09 12:34:45")
            .replace("[#bindNum#]", "6");

    logger.info("templateSend json = " + content);

    try {
      JsonParser jsonParser = new JsonParser();
      content = jsonParser.parse(content).toString();
//      wxService.templateSend(content);
    } catch (Exception e) {
      logger.error("templateSend ERROR" ,e);
    }
  }

  public void sendMessageToDeviceCloud() {
    String content = "{\"sn\":\"M7001504CNPBMKCB\",\"asy_error_code\":0,\"asy_error_msg\":\"succ\",\"msg_id\":37350,\"msg_type\":\"set\",\"mandatory_services\":{\"operation_status\":{\"status\":1}}}";
    content = new JsonParser().parse(content).toString();
    try {
        String response = wxService.sendMessageToDeviceCloud(content);
        logger.info("sendMessageToDeviceCloud response = {}", response);
    } catch (Exception e) {
        logger.error("sendMessageToDeviceCloud ", e);
    }
  }

}
