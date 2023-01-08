package com.gmail.thelilchicken01.tff.client;

import java.util.Comparator;

public class HandlerPriorityComparator implements Comparator<PrioritizedHandler> {

	@Override
	public int compare(PrioritizedHandler o1, PrioritizedHandler o2) {
		
		int ret = 10*(o1.getPriority().ordinal() - o2.getPriority().ordinal());
		
		return ret == 0 ? 1 : ret;
	}

}
