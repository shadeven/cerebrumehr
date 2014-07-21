package com.oreon.cerebrum;

public class MetaData {

	public static final String[][] ARR_FIELDS = {

	{"com.oreon.cerebrum.encounter.Encounter",

	"patientNote", "java.lang.String",

	"prescribedTestsCount", "java.lang.Integer",

	"vitals.SysBP", "java.lang.Integer",

	"vitals.DiasBP", "java.lang.Integer",

	"vitals.Temperature", "java.lang.Double",

	"vitals.Pulse", "java.lang.Integer",

	"vitals.RespirationRate", "java.lang.Integer",

	"prescription.displayName", "java.lang.String",

	"patient.displayName", "java.lang.String",

	"creator.displayName", "java.lang.String",

	"tests", "java.lang.String",

	"simpleCodesCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.encounter.PrescribedTest",

	"dxTest.displayName", "java.lang.String",

	"encounter.displayName", "java.lang.String",

	"remarks", "java.lang.String",

	"testResults.results", "java.lang.String",

	"testResults.document", "FileAttachment",

	},

	{"com.oreon.cerebrum.encounter.Differential",

	"remarks", "java.lang.String",

	},

	{"com.oreon.cerebrum.encounter.Reason",

	"code.displayName", "java.lang.String",

	"remarks", "java.lang.String",

	},

	{"com.oreon.cerebrum.billing.Invoice",

	"invoiceItemsCount", "java.lang.Integer",

	"patient.displayName", "java.lang.String",

	"notes", "java.lang.String",

	"totalAmount", "BigDecimal",

	"paidAmount", "BigDecimal",

	},

	{"com.oreon.cerebrum.billing.Service",

	"name", "java.lang.String",

	"price", "BigDecimal",

	},

	{"com.oreon.cerebrum.billing.InvoiceItem",

	"units", "java.lang.Integer",

	"service.displayName", "java.lang.String",

	"invoice.displayName", "java.lang.String",

	"appliedPrice", "BigDecimal",

	"total", "BigDecimal",

	},

	{"com.oreon.cerebrum.unusualoccurences.UnusualOccurence",

	"occurenceType.displayName", "java.lang.String",

	"category.name", "java.lang.String",

	"severity.name", "java.lang.String",

	"description", "java.lang.String",

	"patient.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.unusualoccurences.OccurenceType",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.Patient",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"dateOfBirth", "java.util.Date",

	"gender.name", "java.lang.String",

	"contactDetails.primaryPhone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.email", "java.lang.String",

	"contactDetails.age", "java.lang.Integer",

	"contactDetails.title.name", "java.lang.String",

	"admissionsCount", "java.lang.Integer",

	"prescriptionsCount", "java.lang.Integer",

	"address.streetAddress", "java.lang.String",

	"address.city", "java.lang.String",

	"address.State", "java.lang.String",

	"address.phone", "java.lang.String",

	"unusualOccurencesCount", "java.lang.Integer",

	"patientDocumentsCount", "java.lang.Integer",

	"allergysCount", "java.lang.Integer",

	"immunizationsCount", "java.lang.Integer",

	"healthNumber", "java.lang.String",

	"vitalValuesCount", "java.lang.Integer",

	"history.medicalHistory", "java.lang.String",

	"history.socialHistory", "java.lang.String",

	"history.familyHistory", "java.lang.String",

	"history.medications", "java.lang.String",

	"history.allergies", "java.lang.String",

	"encountersCount", "java.lang.Integer",

	"appliedChartsCount", "java.lang.Integer",

	"chartProceduresCount", "java.lang.Integer",

	"bed.displayName", "java.lang.String",

	"chronicConditionsCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.patient.PatientDocument",

	"name", "java.lang.String",

	"file", "FileAttachment",

	"notes", "java.lang.String",

	"patient.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.Allergy",

	"patient.displayName", "java.lang.String",

	"severity.name", "java.lang.String",

	"drug.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.Immunization",

	"date", "java.util.Date",

	"patient.displayName", "java.lang.String",

	"vaccine.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.Vaccine",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.patient.TrackedVital",

	"name", "java.lang.String",

	"minVal", "java.lang.Double",

	"maxVal", "java.lang.Double",

	},

	{"com.oreon.cerebrum.patient.VitalValue",

	"value", "java.lang.Double",

	"trackedVital.displayName", "java.lang.String",

	"patient.displayName", "java.lang.String",

	"remarks", "java.lang.String",

	},

	{"com.oreon.cerebrum.facility.Facility",

	"name", "java.lang.String",

	"wardsCount", "java.lang.Integer",

	"isResidential", "java.lang.Boolean",

	},

	{"com.oreon.cerebrum.facility.Room",

	"bedsCount", "java.lang.Integer",

	"name", "java.lang.String",

	"roomType.displayName", "java.lang.String",

	"ward.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.facility.Bed",

	"room.displayName", "java.lang.String",

	"name", "java.lang.String",

	"patient.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.facility.Ward",

	"facility.displayName", "java.lang.String",

	"roomsCount", "java.lang.Integer",

	"name", "java.lang.String",

	"gender.name", "java.lang.String",

	},

	{"com.oreon.cerebrum.facility.RoomType",

	"name", "java.lang.String",

	"description", "java.lang.String",

	"rate", "java.lang.Double",

	"numberOfBeds", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.ddx.Finding",

	"name", "java.lang.String",

	"differentialDxsCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.ddx.PhysicalFinding",

	"name", "java.lang.String",

	"differentialDxsCount", "java.lang.Integer",

	"icdCode", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.LabFinding",

	"name", "java.lang.String",

	"differentialDxsCount", "java.lang.Integer",

	"testName", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.Disease",

	"gender.name", "java.lang.String",

	"name", "java.lang.String",

	"differentialDiagnosesCount", "java.lang.Integer",

	"relatedDisease.displayName", "java.lang.String",

	"conditionCategory.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.ConditionFinding",

	"disease.displayName", "java.lang.String",

	"falsePositive", "java.lang.Boolean",

	},

	{"com.oreon.cerebrum.ddx.ConditionCategory",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.DifferentialDx",

	"name", "java.lang.String",

	"dxCategory.displayName", "java.lang.String",

	"finding.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.DxCategory",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.PatientFinding",

	"finding.displayName", "java.lang.String",

	"patientDiffDx.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.PatientDiffDx",

	"patientFindingsCount", "java.lang.Integer",

	"patient.displayName", "java.lang.String",

	"notes", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.DxTest",

	"name", "java.lang.String",

	"description", "java.lang.String",

	},

	{"com.oreon.cerebrum.ddx.ChronicCondition",

	"name", "java.lang.String",

	"patientsCount", "java.lang.Integer",

	"chartsCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.prescription.PrescriptionTemplate",

	"name", "java.lang.String",

	"directivesForPatient", "java.lang.String",

	"prescriptionItemTemplatesCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.prescription.Prescription",

	"prescriptionItemsCount", "java.lang.Integer",

	"patient.displayName", "java.lang.String",

	"directivesForPatient", "java.lang.String",

	"active", "java.lang.Boolean",

	"drugs", "java.lang.String",

	},

	{"com.oreon.cerebrum.prescription.PrescriptionItem",

	"drug.displayName", "java.lang.String",

	"qty", "java.lang.Double",

	"strength", "java.lang.String",

	"prescription.displayName", "java.lang.String",

	"route.name", "java.lang.String",

	"duration", "java.lang.Integer",

	"frequency.displayName", "java.lang.String",

	"remarks", "java.lang.String",

	"brandName", "java.lang.String",

	},

	{"com.oreon.cerebrum.prescription.PrescriptionItemTemplate",

	"drug.displayName", "java.lang.String",

	"qty", "java.lang.Double",

	"frequency.displayName", "java.lang.String",

	"strength", "java.lang.String",

	"route.name", "java.lang.String",

	"duration", "java.lang.Integer",

	"remarks", "java.lang.String",

	"brandName", "java.lang.String",

	"prescriptionTemplate.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.prescription.Frequency",

	"name", "java.lang.String",

	"qtyPerDay", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.appointment.Appointment",

	"start", "java.util.Date",

	"end", "java.util.Date",

	"physician.displayName", "java.lang.String",

	"patient.displayName", "java.lang.String",

	"remarks", "java.lang.String",

	"units", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.employee.Employee",

	"firstName", "java.lang.String",

	"lastName", "java.lang.String",

	"dateOfBirth", "java.util.Date",

	"gender.name", "java.lang.String",

	"contactDetails.primaryPhone", "java.lang.String",

	"contactDetails.secondaryPhone", "java.lang.String",

	"contactDetails.email", "java.lang.String",

	"contactDetails.age", "java.lang.Integer",

	"contactDetails.title.name", "java.lang.String",

	"employeeNumber", "java.lang.String",

	"appUser.displayName", "java.lang.String",

	"facility.displayName", "java.lang.String",

	"department.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.Physician",

	"employeeNumber", "java.lang.String",

	"appUser.displayName", "java.lang.String",

	"facility.displayName", "java.lang.String",

	"department.displayName", "java.lang.String",

	"specialization.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.Nurse",

	"employeeNumber", "java.lang.String",

	"appUser.displayName", "java.lang.String",

	"facility.displayName", "java.lang.String",

	"department.displayName", "java.lang.String",

	"nurseSpecialty.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.Technician",

	"employeeNumber", "java.lang.String",

	"appUser.displayName", "java.lang.String",

	"facility.displayName", "java.lang.String",

	"department.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.Clerk",

	"employeeNumber", "java.lang.String",

	"appUser.displayName", "java.lang.String",

	"facility.displayName", "java.lang.String",

	"department.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.Specialization",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.NurseSpecialty",

	"name", "java.lang.String",

	},

	{"com.oreon.cerebrum.employee.Department",

	"name", "java.lang.String",

	"employeesCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.drugs.AtcDrug",

	"code", "java.lang.String",

	"name", "java.lang.String",

	"subcategoriesCount", "java.lang.Integer",

	"drug.displayName", "java.lang.String",

	"parent.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.drugs.Drug",

	"name", "java.lang.String",

	"absorption", "java.lang.String",

	"biotransformation", "java.lang.String",

	"atcCodes", "java.lang.String",

	"contraIndication", "java.lang.String",

	"description", "java.lang.String",

	"dosageForm", "java.lang.String",

	"foodInteractions", "java.lang.String",

	"halfLife", "java.lang.String",

	"halfLifeNumberOfHours", "java.lang.Double",

	"indication", "java.lang.String",

	"mechanismOfAction", "java.lang.String",

	"patientInfo", "java.lang.String",

	"pharmacology", "java.lang.String",

	"drugInteractionsCount", "java.lang.Integer",

	"drugCategorysCount", "java.lang.Integer",

	"toxicity", "java.lang.String",

	"routeOfElimination", "java.lang.String",

	"volumeOfDistribution", "java.lang.String",

	"drugBankId", "java.lang.String",

	"categories", "java.lang.String",

	},

	{"com.oreon.cerebrum.drugs.DrugInteraction",

	"description", "java.lang.String",

	"drug.displayName", "java.lang.String",

	"interactingDrug.displayName", "java.lang.String",

	"severity.name", "java.lang.String",

	},

	{"com.oreon.cerebrum.drugs.DrugCategory",

	"name", "java.lang.String",

	"drugsCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.users.AppUser",

	"userName", "java.lang.String",

	"password", "java.lang.String",

	"enabled", "java.lang.Boolean",

	"appRolesCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.users.AppRole",

	"name", "java.lang.String",

	"appUsersCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.codes.Code",

	"name", "java.lang.String",

	"description", "java.lang.String",

	"includes", "java.lang.String",

	"notIncludedHere", "java.lang.String",

	"codeFirst", "java.lang.String",

	"section.displayName", "java.lang.String",

	"notCodedHere", "java.lang.String",

	"codeAlso", "java.lang.String",

	},

	{"com.oreon.cerebrum.codes.Chapter",

	"name", "java.lang.String",

	"description", "java.lang.String",

	"sectionsCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.codes.Section",

	"name", "java.lang.String",

	"description", "java.lang.String",

	"chapter.displayName", "java.lang.String",

	"codesCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.codes.SimpleCode",

	"name", "java.lang.String",

	"encountersCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.charts.AppliedChart",

	"patient.displayName", "java.lang.String",

	"chart.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.charts.Chart",

	"chartItemsCount", "java.lang.Integer",

	"name", "java.lang.String",

	"chronicCondition.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.charts.ChartItem",

	"name", "java.lang.String",

	"duration", "java.lang.Integer",

	"chart.displayName", "java.lang.String",

	"frequencyPeriod.name", "java.lang.String",

	},

	{"com.oreon.cerebrum.charts.ChartProcedure",

	"patient.displayName", "java.lang.String",

	"chartItem.displayName", "java.lang.String",

	"dueDate", "java.util.Date",

	"datePerformed", "java.util.Date",

	"remarks", "java.lang.String",

	},

	{"com.oreon.cerebrum.settings.Settings",

	},

	{"com.oreon.cerebrum.settings.Setting",

	"value", "java.lang.String",

	"settingName.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.settings.SettingName",

	"name", "java.lang.String",

	"settingGroup.displayName", "java.lang.String",

	"defaultValue", "java.lang.String",

	},

	{"com.oreon.cerebrum.settings.SettingGroup",

	"name", "java.lang.String",

	"settingNamesCount", "java.lang.Integer",

	},

	{"com.oreon.cerebrum.admission.Admission",

	"patient.displayName", "java.lang.String",

	"admissionNote", "java.lang.String",

	"dischargeDate", "java.util.Date",

	"bedStaysCount", "java.lang.Integer",

	"dischargeNote", "java.lang.String",

	"dischargeCode.name", "java.lang.String",

	"currentBed", "java.lang.String",

	"isCurrent", "java.lang.Boolean",

	"invoice.displayName", "java.lang.String",

	},

	{"com.oreon.cerebrum.admission.BedStay",

	"fromDate", "java.util.Date",

	"toDate", "java.util.Date",

	"admission.displayName", "java.lang.String",

	"bed.displayName", "java.lang.String",

	},

	};

}
