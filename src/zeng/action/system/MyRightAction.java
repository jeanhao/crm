package zeng.action.system;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import zeng.action.BaseAction;
import zeng.entity.SysRight;
import zeng.tool.Pager;

public class MyRightAction extends BaseAction<SysRight> implements ModelDriven<Pager>{
	
	private static final long serialVersionUID = 1L;
	private Pager pager;
	public String getMyRight(){
		Integer start = Integer.valueOf(pager.getStart());
		Integer last = Integer.valueOf(pager.getLimit())+start;
		List<String> rightList = super.getLoginUserRight();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		for(;start < last && start < rightList.size();start++){
			jsonObject.put("name",rightList.get(start));
			jsonArray.add(jsonObject);
		}
		jsonMap.put("rowCount",rightList.size());
		jsonMap.put("entity",jsonArray); 
//		String userName = super.getLoginUserName();
//		if(userName != null){
//			List<String> rightList =  baseService.query("select r.text  from SysRight r , SysRoleRight rr , SysUser u where " +
//				"r.id = rr.sysRight.id and rr.sysRole.id = u.sysRole.id and u.userName = ?",userName);
//			getJsonMap().put("entity",rightList); 
//		}
		return SUCCESS;
	}
	@Override
	public Pager getModel() {
		pager = new Pager();
		return pager;
	}
}
