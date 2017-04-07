package govsystem.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * Created by Myth on 3/26/2017.
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    public ModelAndView test(){
        ModelAndView mav = new ModelAndView();
        List<String> list = new LinkedList<>();
        list.add("串0");
        list.add("串1");
        list.add("串2");
        list.add("串3");
        list.add("串4");
        mav.addObject("list",list);
        mav.setViewName("/testpages/view");
        return mav;
    }
    @RequestMapping("/file")
    String file() {
        return "testpages/file";
    }
    @RequestMapping("springUpload")
    public String  springUpload(HttpServletRequest request) throws IllegalStateException, IOException
    {
        long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    //" ‘/’相对于项目GovSystem"
                    // 文件保存路径
                    String filePath = request.getSession().getServletContext().getRealPath("/") + "mp4/"
                            + file.getOriginalFilename();
                    File f = new File(filePath);
                    // 转存文件
                    file.transferTo(f);


                }
            }
            //System.out.println("上下文路径： "+);;//获得上下文路径
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "testpages/success";
    }

    @RequestMapping("/radio")
    String radio() {
        return "testpages/testradio";
    }


}
