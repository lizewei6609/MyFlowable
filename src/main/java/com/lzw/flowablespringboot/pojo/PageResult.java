package com.lzw.flowablespringboot.pojo;
import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果封装对象
 * @author auth
 */
@Data
public class PageResult implements Serializable {

	private static final long serialVersionUID = 1L;

	// 总记录数
	private Long recordsFiltered;
	// 当前页结果
	private List data;

	public PageResult(Page p) {
		super();
		this.recordsFiltered = p.getTotal();
		this.data = p.getResult();
	}
}
