package zeng.tool;


public class Pager {
	private Integer start;	//页码初始列
	private Integer limit;	//每页内容数
	private String sort;//排序字段的名字
	private String dir;//排序方向
	//private String orderColumn;	//用于排序的名称集,要求封装成json格式传入
	private String	searchColumn;		//用于搜索的名称集,要求封装封json格式传入
//	private Integer totalCount;//数据的总量
	//private String paraMap;		//参数集，封装成json数据传入
	//构造函数
	public Pager(Integer start, Integer limit) {
		this.start = start;
		this.limit = limit;
	}
	public Pager() {
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getSearchColumn() {
		return searchColumn;
	}
	public void setSearchColumn(String searchColumn) {
		this.searchColumn = searchColumn;
	}
//	public Integer getTotalCount() {
//		return totalCount;
//	}
//	public void setTotalCount(Integer totalCount) {
//		this.totalCount = totalCount;
//	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
}
