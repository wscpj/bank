package com.bank.common.service.local;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.bank.common.base.BaseService;
import com.bank.common.service.BackupService;
import com.bank.common.util.PropertiesUtil;

public class BackupServiceImpl extends BaseService implements BackupService {
    /**
     *
     */
    private static final long serialVersionUID = -7214532142690758512L;
    private Logger logger = Logger.getLogger(BackupServiceImpl.class);
    @Override
    public Boolean backupDataBase() {
        logger.info("backupDataBase paramters : {}");
        try {
            this.export();
            return Boolean.TRUE;
        } catch (IOException e) {
            logger.error("backupDataBase error" + e);
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    private String getExportCommand() throws IOException {
        StringBuffer command = new StringBuffer();
        String mysqlpath = (String) PropertiesUtil.getContextProperty("backup.mysql.path");//本地mysql的bin目录
        String username = (String) PropertiesUtil.getContextProperty("master.jdbc.username");//用户名
        String password = (String) PropertiesUtil.getContextProperty("master.jdbc.password");//密码
        String host = (String) PropertiesUtil.getContextProperty("backup.database.host");//导入的目标数据库所在的主机
        String port = (String) PropertiesUtil.getContextProperty("backup.database.port");//使用的端口号
        String exportDatabaseName = (String) PropertiesUtil.getContextProperty("backup.database.name");//需要导出的数据库名
        String exportPath = (String) PropertiesUtil.getContextProperty("backup.database.exportPath");//导出路径
        //注意哪些地方要空格，哪些不要空格
        command.append(mysqlpath).append("mysqldump -u").append(username).append(" -p").append(password)//密码是用的小p，而端口是用的大P。
        .append(" -h").append(host).append(" -P").append(port).append(" ").append(exportDatabaseName).append(" -r ").append(exportPath);
        return command.toString();
    }

    private String[] getImportCommand() {
        String mysqlpath = (String) PropertiesUtil.getContextProperty("backup.mysql.path");//本地mysql的bin目录
        String username = (String) PropertiesUtil.getContextProperty("master.jdbc.username");//用户名
        String password = (String) PropertiesUtil.getContextProperty("master.jdbc.password");//密码
        String host = (String) PropertiesUtil.getContextProperty("backup.database.host");//导入的目标数据库所在的主机
        String port = (String) PropertiesUtil.getContextProperty("backup.database.port");//使用的端口号
        String importDatabaseName = (String) PropertiesUtil.getContextProperty("backup.database.name");//导入的目标数据库的名称
        String importPath = (String) PropertiesUtil.getContextProperty("backup.database.importPath");//导入的目标文件所在的位置
        //第一步，获取登录命令语句
        String loginCommand = new StringBuffer().append(mysqlpath).append("mysql -u").append(username).append(" -p").append(password).append(" -h").append(host)
                .append(" -P").append(port).toString();
        //第二步，获取切换数据库到目标数据库的命令语句
        String switchCommand = new StringBuffer("use ").append(importDatabaseName).toString();
        //第三步，获取导入的命令语句
        String importCommand = new StringBuffer("source ").append(importPath).toString();
        //需要返回的命令语句数组
        String[] commands = new String[] {loginCommand, switchCommand, importCommand};
        return commands;
    }
    /*
     * export database
     * @author zhangyapo
     * @date
     */
    public void export() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String command = getExportCommand();
        System.out.println(command);
        runtime.exec(command);//这里简单一点异常我就直接往上抛
    }

    /*
     * import database sql
     * @author zhangyapo
     * @date
     */
    public void importSql(Properties properties) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String cmdarray[] = getImportCommand();
        Process process = runtime.exec(cmdarray[0]);
        OutputStream os = process.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        writer.write(cmdarray[1] + "\r\n" + cmdarray[2]);
        writer.flush();
        writer.close();
        os.close();
    }
}
