import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

public class quaintest {


        // 上传本地文件
        @Test
        public void uploadFile(){
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone2());
            //...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
            //...生成上传凭证，然后准备上传
            String accessKey = "5hhWlnJX4ePF_dsC7RPFAAI26Z5gUGb5PT54lgko";
            String secretKey = "Q7A_uszfRtvBsohw_avrgomh-vzyCk1bVJbilD14";
            String bucket = "maie-learn";
            //如果是Windows情况下，格式是 D:\\qiniu\\test.png，可支持中文
            String localFilePath = "C:/Users/lenovo/Pictures/test.jfif";
            //默认不指定key的情况下，以文件内容的hash值作为文件名
            String key = null;
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(localFilePath, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException e) {
                    e.printStackTrace();
                }
            }
        }
}
