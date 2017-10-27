package com.shuaichen.action;

import com.opensymphony.xwork2.ActionSupport;
import com.shuaichen.domain.Area;
import com.shuaichen.domain.Server;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 17/10/27.
 */
public class CitySelectAction extends ActionSupport{

    private List<Area> areaList;
    // 定义变量用来接受选择的是哪一个大区
    private int index;
    private List<Server> servers;

    public CitySelectAction() {
        areaList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Area area = new Area();
            area.setAname("网通" + (i+1) + "区");
            area.setId(i);
            // 在每个大区中创建10个服务器
            List<Server> servers = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Server s = new Server();
                s.setId(j);
                s.setSname((i+1) + "区:小霸王" + (j+1) + "号");
                // 存储服务器
                servers.add(s);
            }
            // 把服务器放到大区对象中
            area.setServers(servers);
            areaList.add(area);
        }
    }

    public String getServer() throws IOException {

        // 返回json数据
        // 获取选择的大区中服务器的数据
        List<Server> servers = areaList.get(index).getServers();
        // 把集合数据转换为json格式,写入到响应体中
        String json = JSONArray.fromObject(servers).toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        // 设置相应头
        response.setContentType("application/json;charset=utf-8");
        // 写入数据
        response.getWriter().write(json);
        return null;
    }

    // 使用struts2的方式向客户端返回json数据
    public String getServerJson(){
        // 给需要转化的数据类对象赋值
        // 获取某个大区中的所有服务器的集合
        // 注意:  数据类必须为成员变量
        servers = areaList.get(index).getServers();



        return SUCCESS;
    }


    public String playGame(){
        return SUCCESS;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
}
