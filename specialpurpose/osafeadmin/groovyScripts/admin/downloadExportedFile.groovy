import org.apache.ofbiz.base.util.UtilValidate;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.RandomAccessFile;
import org.apache.ofbiz.base.util.*
import org.apache.ofbiz.base.util.string.*;
import java.util.LinkedList;
import java.util.HashMap;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.product.store.ProductStoreWorker;
import org.apache.ofbiz.base.util.UtilDateTime;
import com.osafe.util.OsafeAdminUtil;

    /*Send Customer XML for browser.*/
    if(parameters.allowSaveAfterExport == 'Y')
    {
        if(UtilValidate.isNotEmpty(parameters.exportedFilePath) && UtilValidate.isNotEmpty(parameters.exportedFileName) && UtilValidate.isNotEmpty(parameters.downloadedFileName))
        {
	        response.setContentType("text/xml");
	        exportedFilePath = parameters.exportedFilePath;
	        exportedFileName = parameters.exportedFileName;
	        response.setHeader("Content-Disposition","attachment; filename=\"" + parameters.downloadedFileName + "\";");
	        InputStream inputStr = new FileInputStream(exportedFilePath + exportedFileName);
	        OutputStream out = response.getOutputStream();
	        byte[] bytes = new byte[102400];
	        int bytesRead;
	        while ((bytesRead = inputStr.read(bytes)) != -1)
	        {
	            out.write(bytes, 0, bytesRead);
	        }
	        out.flush();
	        out.close();
	        inputStr.close();
	        return "success";
        } else{
            return "notDownload";
        }
    } else{
       return "notDownload";
    }
