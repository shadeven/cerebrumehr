package com.oreon.cerebrum.web.action.settings;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.settings.Settings;

//
public abstract class SettingsActionBase extends BaseAction<Settings>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long settingsId;

	public void setSettingsId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setSettingsIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getSettingsId() {
		return (Long) getId();
	}

	public Settings getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Settings t) {
		this.instance = t;
		loadAssociations();
	}

	public Settings getSettings() {
		return (Settings) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('settings', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('settings', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Settings createInstance() {
		Settings instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

	}

	public void wire() {
		getInstance();

	}

	public Settings getDefinedInstance() {
		return (Settings) (isIdDefined() ? getInstance() : null);
	}

	public void setSettings(Settings t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setSettingsId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Settings> getEntityClass() {
		return Settings.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewSettings() {
		load(currentEntityId);
		return "viewSettings";
	}

}
