create unique index IX_6F8E093A on FinServices_FinAccount (groupId, accountNumber[$COLUMN_LENGTH:75$]);
create index IX_3623D7FD on FinServices_FinAccount (groupId, ownerUserId);
create unique index IX_B5DBCFE8 on FinServices_FinAccount (groupId, uuid_[$COLUMN_LENGTH:75$]);
create index IX_2C2461D6 on FinServices_FinAccount (uuid_[$COLUMN_LENGTH:75$]);

create index IX_1AF9A747 on FinServices_FinCreditApp (groupId, accountId);
create unique index IX_25D2DA4F on FinServices_FinCreditApp (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D12BB67D on FinServices_FinTransaction (groupId, accountId);
create unique index IX_45A8F485 on FinServices_FinTransaction (uuid_[$COLUMN_LENGTH:75$], groupId);