package com.hyp.learn.cf.commons.object;

import lombok.Data;

import java.util.List;

/**
* @ClassName:       PageVO
*                   分页VO
*/
@Data
public class PageVO<T>  {
	/**
	 * serial_number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 总记录数
	 */
	private Long totalRows;
	/**
	 * 总页数
	 */
	private Long totalPages;
	/**
	 * 当前第几页
	 */
	private Long pageNum;
	/**
	 * 每页记录数
	 */
	private Long pageSize;
	/**
	 * 当前页记录数
	 */
	private Integer curPageSize;
	/**
	 * 数据列表
	 */
	private List<T> list;

}
