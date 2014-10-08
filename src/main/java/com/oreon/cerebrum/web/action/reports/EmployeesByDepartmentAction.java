package com.oreon.cerebrum.web.action.reports;

import org.witchcraft.jasperreports.BaseReportAction;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.beanutils.PropertyUtils;

import org.jboss.seam.annotations.Name;

import org.witchcraft.jasperreports.BaseReportAction;

import java.util.Date;
import org.witchcraft.utils.DateUtils;

import com.oreon.cerebrum.ProjectUtils;

@Name("employeesByDepartmentAction")
public class EmployeesByDepartmentAction extends BaseReportAction {

	@SuppressWarnings("unchecked")
	@Override
	public void updateParameters(Map map) {

	}

}
