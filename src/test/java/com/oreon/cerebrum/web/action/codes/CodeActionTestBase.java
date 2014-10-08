package com.oreon.cerebrum.web.action.codes;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.codes.Code;

public class CodeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Code> {

	CodeAction codeAction = new CodeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Code> getAction() {
		return codeAction;
	}

}
