package util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Testsql {
    public static void main(String[] args) {
        try {
            writeToFile();
            //readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * DOC 往文件里写入数据.
     *
     * @throws IOException
     */
    private static void writeToFile() throws IOException {
        String writerContent = "";// 要写入的文本
        File file = new File("D:\\user.txt");// 要写入的文本文件
        if (!file.exists()) {// 如果文件不存在，则创建该文件
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);// 获取该文件的输出流
        for (int i = 65 ;i < 5000; i ++ ) {
            writerContent = "insert into userprize(name,ip,prize,date,coment,number,state) VALUES ("+"'李弟文"+i+"',"+"'14.118.138."+i+"',"+"'一等奖',"
                    +"'"+calcTime("", -(i+1))+"'"+","+"'恭喜你获得价值588元的阅读神器kindle一台，运气不错，三等奖哟，祝贺你亲',"+"'136312761"+i+"',"+"'"+i+"'"
                    +");"+"\r\n";
            writer.write(writerContent);// 写内容
        }
        writer.flush();// 清空缓冲区，立即将输出流里的内容写到文件里
        writer.close();// 关闭输出流，施放资源
    }

    /**
     *
     * @param type 往前计算的类型（week、month、year，“”表示day
     * @param count 往前计算的数量
     * @return
     */
    private static String calcTime(String type ,int count){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        if (type .equals("week")) {
            //周
            calendar.add(Calendar.WEEK_OF_YEAR, count);
        }else if (type .equals("month")) {
            //月
            calendar.add(Calendar.MONTH, count);
        }else if (type .equals("year")) {
            //12个月
            calendar.add(Calendar.MONTH, count);
        }else {
            //日
            calendar.add(Calendar.DATE, count);
        }
        java.util.Date date = calendar.getTime();
        return sdf.format(date);

    }

}