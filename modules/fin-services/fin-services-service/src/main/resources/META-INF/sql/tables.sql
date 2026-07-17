create table FinServices_FinAccount (
	uuid_ VARCHAR(75) null,
	accountId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	ownerUserId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	accountNumber VARCHAR(75) null,
	accountName VARCHAR(75) null,
	accountType VARCHAR(75) null,
	balance DOUBLE,
	status INTEGER,
	cardScheme VARCHAR(75) null,
	cardBrand VARCHAR(75) null,
	cardBankName VARCHAR(75) null,
	cardCountryName VARCHAR(75) null
);

create table FinServices_FinCreditApp (
	uuid_ VARCHAR(75) null,
	creditAppId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	accountId LONG,
	applicantName VARCHAR(75) null,
	requestedAmount DOUBLE,
	purpose VARCHAR(75) null,
	status INTEGER,
	reviewNotes VARCHAR(75) null
);

create table FinServices_FinTransaction (
	uuid_ VARCHAR(75) null,
	transactionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	accountId LONG,
	transactionType VARCHAR(75) null,
	amount DOUBLE,
	description VARCHAR(75) null,
	transactionDate DATE null,
	status INTEGER
);