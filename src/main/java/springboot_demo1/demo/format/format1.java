package springboot_demo1.demo.format;

import Test.demo;
import com.mathworks.toolbox.javabuilder.MWException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import shichangfa.demo111;

//java.library.path   :/Users/dongbudong/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:.
@Controller
public class format1 {

    @RequestMapping(value = "/format1", method = RequestMethod.GET)
    @ResponseBody
    public static void main(String[] args) throws MWException {
        System.out.println("java.library.path    " + System.getProperty("java.library.path"));
        System.out.println("……………………");
        System.out.println("环境变量" + System.getenv());




//        String str = System.mapLibraryName("java.library.path");
//        System.loadLibrary("libmwmclmcrrt.9.3.dylib");
//        System.out.println(str);
//        System.load("/Applications/MATLAB_R2017b.app/bin/maci64/libmwmclmcrrt.9.3.dylib");//可以加载

//        System.load("/Library/Java/Extensions/libmwmclmcrrt.9.3.dylib");//  /Applications/MATLAB_R2017b.app/bin/maci64/libmwmclmcrrt.9.3.dylib

//
//        try {
//            System.out.println("***************");
//            demo d = new demo();
//        } catch (com.mathworks.toolbox.javabuilder.MWException e) {
//            e.printStackTrace();
//
//        }

    }


}
