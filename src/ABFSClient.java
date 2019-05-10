/**
 * Created by mayankthirani on 4/8/19.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.fs.RemoteIterator;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

//import org.apache.hadoop.hdfs.DistributedFileSystem;
//import org.apache.hadoop.fs.azurebfs.AzureBlobFileSystem;

public class ABFSClient {
    public static void main(String[] args) throws IOException, URISyntaxException
    {
        String ip = "hdfs://10.0.85.141:9870";
        String hostname = "hdfs://na77sl-ihdc-ux02130.clouddev.snaplogic.com:9870";
        String abfsUri = "abfs://filesystem2@adlsgen2test1.dfs.core.windows.net";
        String abfsPath = abfsUri + "/";

        Configuration conf = new Configuration();

        //HDFS
        conf.set("fs.hdfs.impl.disable.cache", "true");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.fs.DistributedFileSystem");

        //ABFS
        conf.set("fs.abfs.impl.disable.cache", "true");
        conf.set("fs.azure.createRemoteFileSystemDuringInitialization", "true");
        conf.set("fs.abfs.impl", "org.apache.hadoop.fs.azurebfs.AzureBlobFileSystem");
        //conf.set("fs.abfs.impl", "com.example.AzureBlobFileSystem");

        conf.set("fs.azure.account.auth.type", "OAuth");
        conf.set("fs.azure.account.oauth.provider.type",
                "org.apache.hadoop.fs.azurebfs.oauth2.ClientCredsTokenProvider");
        conf.set("fs.azure.account.oauth2.client.endpoint",
                "https://login.microsoftonline.com/3164ebb4-0b34-42ff-820b-25c39783105f/oauth2/token");
        conf.set("fs.azure.account.oauth2.client.id", "e53c9d2a-3ca5-4d75-b628-0f5bbe472e07");
        conf.set("fs.azure.account.oauth2.client.secret", "#)I*=J&;=b>*#}}*}))%&$z_=@;.&gL)@[^|7:*&*@%%:e");

        FileSystem fs = FileSystem.get(new URI(abfsUri), conf);

        if(fs != null) {
            System.out.println("FS not Null");
            System.out.println("FileSystem Class" + fs.getClass());
        }
        else {
            System.out.println("FS NULL ");
        }


        if (fs != null) {
            Path path;
            // Create directory
            path = new Path(fs.getUri().toString() + "/dir1java/");

            boolean dirCreated = fs.mkdirs(path);
            System.out.println("Directory created? " +dirCreated);

            path = new Path(fs.getUri().toString() + "/dir1/");

            // List files
            RemoteIterator<LocatedFileStatus> fileStatusListIterator = fs.listFiles(
                    path, false);
            while(fileStatusListIterator.hasNext()){
                LocatedFileStatus fileStatus = fileStatusListIterator.next();
                //do stuff with the file like ...
//                System.out.println("isFile: " +fileStatus.isDirectory());
//                System.out.println("File: " +fileStatus.getPath());
            }

            FileStatus []fileStatuses = fs.listStatus(path, new PathFilter() {
                @Override
                public boolean accept(final Path path) {

                    //System.out.println(path.getName());
                    return true;
                }
            });
            for(FileStatus f: fileStatuses) {
                System.out.println("File Now: " +f.getPath());
                System.out.println("File Now: " +f.isFile());
            }

//            path = new Path(fs.getUri().toString() + "/dir1");
//            fileStatuses = fs.globStatus(path);
//            for(FileStatus f: fileStatuses) {
//                System.out.println("File Now1: " +f.getPath());
//                System.out.println("File Now1: " +f.isDirectory());
//            }
            //Create file
            Path writeFilePath = new Path("abfs://filesystem2@adlsgen2test1.dfs.core.windows" +
                    ".net/dir1/testWriteOne.txt");
            FSDataOutputStream fsOut = fs.create(writeFilePath);
            List<String> strList = new ArrayList<String>();
            strList.add("One");
            strList.add("Two");
            strList.add("Three");
            if (strList != null) {
                for (String s : strList) {
                    fsOut.writeBytes(s);
                    fsOut.writeBytes("\n");
                }
            }
            fsOut.close();
        }
    }
}
