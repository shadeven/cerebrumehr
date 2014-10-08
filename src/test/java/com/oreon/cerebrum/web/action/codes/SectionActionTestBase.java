package com.oreon.cerebrum.web.action.codes;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.codes.Section;

public class SectionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Section> {

	SectionAction sectionAction = new SectionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Section> getAction() {
		return sectionAction;
	}

}
