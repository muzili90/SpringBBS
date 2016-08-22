package cn.tiger.utils.sort;

import java.util.Comparator;

import cn.tiger.entity.account.AcctAuthority;

public class ComparatorAuthority implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub

		AcctAuthority a = (AcctAuthority) o1;
		AcctAuthority b = (AcctAuthority) o2;

		int flag = a.getId().compareTo(b.getId());

		return flag;
	}

}
