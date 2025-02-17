package test_data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.FtsTableInfo;
import androidx.room.util.TableInfo;
import androidx.room.util.ViewInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.simple.clinic.bloodsugar.BloodSugarMeasurement;
import org.simple.clinic.bloodsugar.BloodSugarMeasurement_RoomDao_Impl;
import org.simple.clinic.bp.BloodPressureMeasurement;
import org.simple.clinic.bp.BloodPressureMeasurement_RoomDao_Impl;
import org.simple.clinic.cvdrisk.CVDRisk;
import org.simple.clinic.cvdrisk.CVDRisk_RoomDao_Impl;
import org.simple.clinic.drugs.PrescribedDrug;
import org.simple.clinic.drugs.PrescribedDrug_RoomDao_Impl;
import org.simple.clinic.drugs.search.Drug;
import org.simple.clinic.drugs.search.Drug_RoomDao_Impl;
import org.simple.clinic.facility.Facility;
import org.simple.clinic.facility.Facility_RoomDao_Impl;
import org.simple.clinic.home.overdue.OverdueAppointment;
import org.simple.clinic.home.overdue.OverdueAppointment_RoomDao_Impl;
import org.simple.clinic.medicalhistory.MedicalHistory;
import org.simple.clinic.medicalhistory.MedicalHistory_RoomDao_Impl;
import org.simple.clinic.overdue.Appointment;
import org.simple.clinic.overdue.Appointment_RoomDao_Impl;
import org.simple.clinic.overdue.callresult.CallResult;
import org.simple.clinic.overdue.callresult.CallResult_RoomDao_Impl;
import org.simple.clinic.patient.Patient;
import org.simple.clinic.patient.PatientAddress;
import org.simple.clinic.patient.PatientAddress_RoomDao_Impl;
import org.simple.clinic.patient.PatientPhoneNumber;
import org.simple.clinic.patient.PatientPhoneNumber_RoomDao_Impl;
import org.simple.clinic.patient.PatientSearchResult;
import org.simple.clinic.patient.PatientSearchResult_RoomDao_Impl;
import org.simple.clinic.patient.Patient_RoomDao_Impl;
import org.simple.clinic.patient.RecentPatient;
import org.simple.clinic.patient.RecentPatient_RoomDao_Impl;
import org.simple.clinic.patient.businessid.BusinessId;
import org.simple.clinic.patient.businessid.BusinessId_RoomDao_Impl;
import org.simple.clinic.patientattribute.PatientAttribute;
import org.simple.clinic.patientattribute.PatientAttribute_RoomDao_Impl;
import org.simple.clinic.protocol.Protocol;
import org.simple.clinic.protocol.ProtocolDrug;
import org.simple.clinic.protocol.ProtocolDrug_RoomDao_Impl;
import org.simple.clinic.protocol.Protocol_RoomDao_Impl;
import org.simple.clinic.questionnaire.Questionnaire;
import org.simple.clinic.questionnaire.Questionnaire_RoomDao_Impl;
import org.simple.clinic.questionnaireresponse.QuestionnaireResponse;
import org.simple.clinic.questionnaireresponse.QuestionnaireResponse_RoomDao_Impl;
import org.simple.clinic.storage.text.TextRecord;
import org.simple.clinic.storage.text.TextRecord_RoomDao_Impl;
import org.simple.clinic.summary.addphone.MissingPhoneReminder;
import org.simple.clinic.summary.addphone.MissingPhoneReminder_RoomDao_Impl;
import org.simple.clinic.summary.teleconsultation.sync.MedicalOfficer;
import org.simple.clinic.summary.teleconsultation.sync.MedicalOfficer_RoomDao_Impl;
import org.simple.clinic.summary.teleconsultation.sync.TeleconsultationFacilityInfo;
import org.simple.clinic.summary.teleconsultation.sync.TeleconsultationFacilityInfo_RoomDao_Impl;
import org.simple.clinic.summary.teleconsultation.sync.TeleconsultationFacilityWithMedicalOfficers;
import org.simple.clinic.summary.teleconsultation.sync.TeleconsultationFacilityWithMedicalOfficers_RoomDao_Impl;
import org.simple.clinic.teleconsultlog.teleconsultrecord.TeleconsultRecord;
import org.simple.clinic.teleconsultlog.teleconsultrecord.TeleconsultRecord_RoomDao_Impl;
import org.simple.clinic.user.OngoingLoginEntry;
import org.simple.clinic.user.OngoingLoginEntry_RoomDao_Impl;
import org.simple.clinic.user.User;
import org.simple.clinic.user.User_RoomDao_Impl;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends RoomDatabase {
    private volatile Patient.RoomDao _patient;

    private volatile PatientAddress.RoomDao _patientAddress;

    private volatile PatientPhoneNumber.RoomDao _patientPhoneNumber;

    private volatile PatientSearchResult.RoomDao _patientSearchResult;

    private volatile BloodPressureMeasurement.RoomDao _bloodPressureMeasurement;

    private volatile PrescribedDrug.RoomDao _prescribedDrug;

    private volatile Facility.RoomDao _facility;

    private volatile User.RoomDao _user;

    private volatile Appointment.RoomDao _appointment;

    private volatile OverdueAppointment.RoomDao _overdueAppointment;

    private volatile MedicalHistory.RoomDao _medicalHistory;

    private volatile OngoingLoginEntry.RoomDao _ongoingLoginEntry;

    private volatile Protocol.RoomDao _protocol;

    private volatile ProtocolDrug.RoomDao _protocolDrug;

    private volatile RecentPatient.RoomDao _recentPatient;

    private volatile BusinessId.RoomDao _businessId;

    private volatile MissingPhoneReminder.RoomDao _missingPhoneReminder;

    private volatile BloodSugarMeasurement.RoomDao _bloodSugarMeasurement;

    private volatile TextRecord.RoomDao _textRecord;

    private volatile TeleconsultationFacilityInfo.RoomDao _teleconsultationFacilityInfo;

    private volatile MedicalOfficer.RoomDao _medicalOfficer;

    private volatile TeleconsultationFacilityWithMedicalOfficers.RoomDao _teleconsultationFacilityWithMedicalOfficers;

    private volatile TeleconsultRecord.RoomDao _teleconsultRecord;

    private volatile Drug.RoomDao _drug;

    private volatile CallResult.RoomDao _callResult;

    private volatile Questionnaire.RoomDao _questionnaire;

    private volatile QuestionnaireResponse.RoomDao _questionnaireResponse;

    private volatile PatientAttribute.RoomDao _patientAttribute;

    private volatile CVDRisk.RoomDao _cVDRisk;

    @Override
    @NonNull
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
        final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(120) {
            @Override
            public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
                db.execSQL("CREATE TABLE IF NOT EXISTS `Patient` (`uuid` TEXT NOT NULL, `addressUuid` TEXT NOT NULL, `fullName` TEXT NOT NULL, `gender` TEXT NOT NULL, `status` TEXT NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, `recordedAt` TEXT NOT NULL, `syncStatus` TEXT NOT NULL, `reminderConsent` TEXT NOT NULL, `deletedReason` TEXT, `registeredFacilityId` TEXT, `assignedFacilityId` TEXT, `retainUntil` TEXT, `eligibleForReassignment` TEXT NOT NULL, `age_value` INTEGER, `age_updatedAt` TEXT, `dateOfBirth` TEXT, PRIMARY KEY(`uuid`), FOREIGN KEY(`addressUuid`) REFERENCES `PatientAddress`(`uuid`) ON UPDATE CASCADE ON DELETE CASCADE )");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_Patient_addressUuid` ON `Patient` (`addressUuid`)");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_Patient_assignedFacilityId` ON `Patient` (`assignedFacilityId`)");
                db.execSQL("CREATE TABLE IF NOT EXISTS `PatientAddress` (`uuid` TEXT NOT NULL, `streetAddress` TEXT, `colonyOrVillage` TEXT, `zone` TEXT, `district` TEXT NOT NULL, `state` TEXT NOT NULL, `country` TEXT, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, PRIMARY KEY(`uuid`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `PatientPhoneNumber` (`uuid` TEXT NOT NULL, `patientUuid` TEXT NOT NULL, `number` TEXT NOT NULL, `phoneType` TEXT NOT NULL, `active` INTEGER NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, PRIMARY KEY(`uuid`), FOREIGN KEY(`patientUuid`) REFERENCES `Patient`(`uuid`) ON UPDATE CASCADE ON DELETE CASCADE )");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_PatientPhoneNumber_patientUuid` ON `PatientPhoneNumber` (`patientUuid`)");
                db.execSQL("CREATE TABLE IF NOT EXISTS `BloodPressureMeasurement` (`uuid` TEXT NOT NULL, `syncStatus` TEXT NOT NULL, `userUuid` TEXT NOT NULL, `facilityUuid` TEXT NOT NULL, `patientUuid` TEXT NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, `recordedAt` TEXT NOT NULL, `systolic` INTEGER NOT NULL, `diastolic` INTEGER NOT NULL, PRIMARY KEY(`uuid`))");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_BloodPressureMeasurement_patientUuid` ON `BloodPressureMeasurement` (`patientUuid`)");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_BloodPressureMeasurement_facilityUuid` ON `BloodPressureMeasurement` (`facilityUuid`)");
                db.execSQL("CREATE TABLE IF NOT EXISTS `PrescribedDrug` (`uuid` TEXT NOT NULL, `name` TEXT NOT NULL, `dosage` TEXT, `rxNormCode` TEXT, `isDeleted` INTEGER NOT NULL, `isProtocolDrug` INTEGER NOT NULL, `patientUuid` TEXT NOT NULL, `facilityUuid` TEXT NOT NULL, `syncStatus` TEXT NOT NULL, `frequency` TEXT, `durationInDays` INTEGER, `teleconsultationId` TEXT, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, PRIMARY KEY(`uuid`))");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_PrescribedDrug_patientUuid` ON `PrescribedDrug` (`patientUuid`)");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_PrescribedDrug_facilityUuid` ON `PrescribedDrug` (`facilityUuid`)");
                db.execSQL("CREATE TABLE IF NOT EXISTS `Facility` (`uuid` TEXT NOT NULL, `name` TEXT NOT NULL, `facilityType` TEXT, `streetAddress` TEXT, `villageOrColony` TEXT, `district` TEXT NOT NULL, `state` TEXT NOT NULL, `country` TEXT NOT NULL, `pinCode` TEXT, `protocolUuid` TEXT, `groupUuid` TEXT, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `syncStatus` TEXT NOT NULL, `deletedAt` TEXT, `syncGroup` TEXT NOT NULL, `location_latitude` REAL, `location_longitude` REAL, `config_diabetesManagementEnabled` INTEGER NOT NULL, `config_teleconsultationEnabled` INTEGER, `config_monthlyScreeningReportsEnabled` INTEGER, `config_monthlySuppliesReportsEnabled` INTEGER NOT NULL, PRIMARY KEY(`uuid`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `LoggedInUser` (`uuid` TEXT NOT NULL, `fullName` TEXT NOT NULL, `phoneNumber` TEXT NOT NULL, `pinDigest` TEXT NOT NULL, `status` TEXT NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `loggedInStatus` TEXT NOT NULL, `registrationFacilityUuid` TEXT NOT NULL, `currentFacilityUuid` TEXT NOT NULL, `teleconsultPhoneNumber` TEXT, `capability_canTeleconsult` TEXT, PRIMARY KEY(`uuid`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `Appointment` (`uuid` TEXT NOT NULL, `patientUuid` TEXT NOT NULL, `facilityUuid` TEXT NOT NULL, `scheduledDate` TEXT NOT NULL, `status` TEXT NOT NULL, `cancelReason` TEXT, `remindOn` TEXT, `agreedToVisit` INTEGER, `appointmentType` TEXT NOT NULL, `syncStatus` TEXT NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, `creationFacilityUuid` TEXT, PRIMARY KEY(`uuid`))");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_Appointment_patientUuid` ON `Appointment` (`patientUuid`)");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_Appointment_creationFacilityUuid` ON `Appointment` (`creationFacilityUuid`)");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_Appointment_facilityUuid` ON `Appointment` (`facilityUuid`)");
                db.execSQL("CREATE TABLE IF NOT EXISTS `MedicalHistory` (`uuid` TEXT NOT NULL, `patientUuid` TEXT NOT NULL, `diagnosedWithHypertension` TEXT NOT NULL, `isOnHypertensionTreatment` TEXT NOT NULL, `isOnDiabetesTreatment` TEXT NOT NULL, `hasHadHeartAttack` TEXT NOT NULL, `hasHadStroke` TEXT NOT NULL, `hasHadKidneyDisease` TEXT NOT NULL, `hasDiabetes` TEXT NOT NULL, `isSmoking` TEXT NOT NULL, `cholesterol_value` REAL, `syncStatus` TEXT NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, PRIMARY KEY(`uuid`))");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_MedicalHistory_patientUuid` ON `MedicalHistory` (`patientUuid`)");
                db.execSQL("CREATE TABLE IF NOT EXISTS `OngoingLoginEntry` (`uuid` TEXT NOT NULL, `phoneNumber` TEXT, `pin` TEXT, `fullName` TEXT, `pinDigest` TEXT, `registrationFacilityUuid` TEXT, `status` TEXT, `createdAt` TEXT, `updatedAt` TEXT, `teleconsultPhoneNumber` TEXT, `capability_canTeleconsult` TEXT, PRIMARY KEY(`uuid`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `Protocol` (`uuid` TEXT NOT NULL, `name` TEXT NOT NULL, `followUpDays` INTEGER NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `syncStatus` TEXT NOT NULL, `deletedAt` TEXT, PRIMARY KEY(`uuid`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `ProtocolDrug` (`uuid` TEXT NOT NULL, `protocolUuid` TEXT NOT NULL, `name` TEXT NOT NULL, `rxNormCode` TEXT, `dosage` TEXT NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, `order` INTEGER NOT NULL, PRIMARY KEY(`uuid`), FOREIGN KEY(`protocolUuid`) REFERENCES `Protocol`(`uuid`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_ProtocolDrug_protocolUuid` ON `ProtocolDrug` (`protocolUuid`)");
                db.execSQL("CREATE TABLE IF NOT EXISTS `BusinessId` (`uuid` TEXT NOT NULL, `patientUuid` TEXT NOT NULL, `metaVersion` TEXT NOT NULL, `meta` TEXT NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, `searchHelp` TEXT NOT NULL, `identifier` TEXT NOT NULL, `identifierType` TEXT NOT NULL, PRIMARY KEY(`uuid`), FOREIGN KEY(`patientUuid`) REFERENCES `Patient`(`uuid`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_BusinessId_patientUuid` ON `BusinessId` (`patientUuid`)");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_BusinessId_identifier` ON `BusinessId` (`identifier`)");
                db.execSQL("CREATE TABLE IF NOT EXISTS `MissingPhoneReminder` (`patientUuid` TEXT NOT NULL, `remindedAt` TEXT NOT NULL, PRIMARY KEY(`patientUuid`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `BloodSugarMeasurements` (`uuid` TEXT NOT NULL, `recordedAt` TEXT NOT NULL, `patientUuid` TEXT NOT NULL, `userUuid` TEXT NOT NULL, `facilityUuid` TEXT NOT NULL, `syncStatus` TEXT NOT NULL, `reading_value` TEXT NOT NULL, `reading_type` TEXT NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, PRIMARY KEY(`uuid`))");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_BloodSugarMeasurements_patientUuid` ON `BloodSugarMeasurements` (`patientUuid`)");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_BloodSugarMeasurements_facilityUuid` ON `BloodSugarMeasurements` (`facilityUuid`)");
                db.execSQL("CREATE TABLE IF NOT EXISTS `TextRecords` (`id` TEXT NOT NULL, `text` TEXT, PRIMARY KEY(`id`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `TeleconsultationFacilityInfo` (`teleconsultationFacilityId` TEXT NOT NULL, `facilityId` TEXT NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, `syncStatus` TEXT NOT NULL, PRIMARY KEY(`teleconsultationFacilityId`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `MedicalOfficer` (`medicalOfficerId` TEXT NOT NULL, `fullName` TEXT NOT NULL, `phoneNumber` TEXT NOT NULL, PRIMARY KEY(`medicalOfficerId`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `TeleconsultationFacilityMedicalOfficersCrossRef` (`teleconsultationFacilityId` TEXT NOT NULL, `medicalOfficerId` TEXT NOT NULL, PRIMARY KEY(`teleconsultationFacilityId`, `medicalOfficerId`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `TeleconsultRecord` (`id` TEXT NOT NULL, `patientId` TEXT NOT NULL, `medicalOfficerId` TEXT NOT NULL, `syncStatus` TEXT NOT NULL, `request_requesterId` TEXT, `request_facilityId` TEXT, `request_requestedAt` TEXT, `request_requesterCompletionStatus` TEXT, `record_recordedAt` TEXT, `record_teleconsultationType` TEXT, `record_patientTookMedicines` TEXT, `record_patientConsented` TEXT, `record_medicalOfficerNumber` TEXT, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, PRIMARY KEY(`id`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `Drug` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `category` TEXT, `frequency` TEXT, `composition` TEXT, `dosage` TEXT, `rxNormCode` TEXT, `protocol` TEXT NOT NULL, `common` TEXT NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, PRIMARY KEY(`id`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `CallResult` (`id` TEXT NOT NULL, `userId` TEXT NOT NULL, `patientId` TEXT, `facilityId` TEXT, `appointmentId` TEXT NOT NULL, `removeReason` TEXT, `outcome` TEXT NOT NULL, `syncStatus` TEXT NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, PRIMARY KEY(`id`))");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_CallResult_appointmentId` ON `CallResult` (`appointmentId`)");
                db.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `PatientFts` USING FTS4(`uuid` TEXT NOT NULL, `fullName` TEXT NOT NULL, content=`Patient`)");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientFts_BEFORE_UPDATE BEFORE UPDATE ON `Patient` BEGIN DELETE FROM `PatientFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientFts_BEFORE_DELETE BEFORE DELETE ON `Patient` BEGIN DELETE FROM `PatientFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientFts_AFTER_UPDATE AFTER UPDATE ON `Patient` BEGIN INSERT INTO `PatientFts`(`docid`, `uuid`, `fullName`) VALUES (NEW.`rowid`, NEW.`uuid`, NEW.`fullName`); END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientFts_AFTER_INSERT AFTER INSERT ON `Patient` BEGIN INSERT INTO `PatientFts`(`docid`, `uuid`, `fullName`) VALUES (NEW.`rowid`, NEW.`uuid`, NEW.`fullName`); END");
                db.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `PatientPhoneNumberFts` USING FTS4(`patientUuid` TEXT NOT NULL, `number` TEXT NOT NULL, content=`PatientPhoneNumber`)");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientPhoneNumberFts_BEFORE_UPDATE BEFORE UPDATE ON `PatientPhoneNumber` BEGIN DELETE FROM `PatientPhoneNumberFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientPhoneNumberFts_BEFORE_DELETE BEFORE DELETE ON `PatientPhoneNumber` BEGIN DELETE FROM `PatientPhoneNumberFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientPhoneNumberFts_AFTER_UPDATE AFTER UPDATE ON `PatientPhoneNumber` BEGIN INSERT INTO `PatientPhoneNumberFts`(`docid`, `patientUuid`, `number`) VALUES (NEW.`rowid`, NEW.`patientUuid`, NEW.`number`); END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientPhoneNumberFts_AFTER_INSERT AFTER INSERT ON `PatientPhoneNumber` BEGIN INSERT INTO `PatientPhoneNumberFts`(`docid`, `patientUuid`, `number`) VALUES (NEW.`rowid`, NEW.`patientUuid`, NEW.`number`); END");
                db.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `BusinessIdFts` USING FTS4(`patientUuid` TEXT NOT NULL, `searchHelp` TEXT NOT NULL, content=`BusinessId`)");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_BusinessIdFts_BEFORE_UPDATE BEFORE UPDATE ON `BusinessId` BEGIN DELETE FROM `BusinessIdFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_BusinessIdFts_BEFORE_DELETE BEFORE DELETE ON `BusinessId` BEGIN DELETE FROM `BusinessIdFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_BusinessIdFts_AFTER_UPDATE AFTER UPDATE ON `BusinessId` BEGIN INSERT INTO `BusinessIdFts`(`docid`, `patientUuid`, `searchHelp`) VALUES (NEW.`rowid`, NEW.`patientUuid`, NEW.`searchHelp`); END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_BusinessIdFts_AFTER_INSERT AFTER INSERT ON `BusinessId` BEGIN INSERT INTO `BusinessIdFts`(`docid`, `patientUuid`, `searchHelp`) VALUES (NEW.`rowid`, NEW.`patientUuid`, NEW.`searchHelp`); END");
                db.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `PatientAddressFts` USING FTS4(`uuid` TEXT NOT NULL, `colonyOrVillage` TEXT, content=`PatientAddress`)");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientAddressFts_BEFORE_UPDATE BEFORE UPDATE ON `PatientAddress` BEGIN DELETE FROM `PatientAddressFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientAddressFts_BEFORE_DELETE BEFORE DELETE ON `PatientAddress` BEGIN DELETE FROM `PatientAddressFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientAddressFts_AFTER_UPDATE AFTER UPDATE ON `PatientAddress` BEGIN INSERT INTO `PatientAddressFts`(`docid`, `uuid`, `colonyOrVillage`) VALUES (NEW.`rowid`, NEW.`uuid`, NEW.`colonyOrVillage`); END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientAddressFts_AFTER_INSERT AFTER INSERT ON `PatientAddress` BEGIN INSERT INTO `PatientAddressFts`(`docid`, `uuid`, `colonyOrVillage`) VALUES (NEW.`rowid`, NEW.`uuid`, NEW.`colonyOrVillage`); END");
                db.execSQL("CREATE TABLE IF NOT EXISTS `Questionnaire` (`uuid` TEXT NOT NULL, `questionnaire_type` TEXT NOT NULL, `layout` TEXT NOT NULL, `deletedAt` TEXT, PRIMARY KEY(`questionnaire_type`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `QuestionnaireResponse` (`uuid` TEXT NOT NULL, `questionnaireId` TEXT NOT NULL, `questionnaireType` TEXT NOT NULL, `facilityId` TEXT NOT NULL, `lastUpdatedByUserId` TEXT, `content` TEXT NOT NULL, `syncStatus` TEXT NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, PRIMARY KEY(`uuid`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `PatientAttribute` (`uuid` TEXT NOT NULL, `patientUuid` TEXT NOT NULL, `userUuid` TEXT NOT NULL, `syncStatus` TEXT NOT NULL, `height` REAL NOT NULL, `weight` REAL NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, PRIMARY KEY(`uuid`))");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_PatientAttribute_patientUuid` ON `PatientAttribute` (`patientUuid`)");
                db.execSQL("CREATE TABLE IF NOT EXISTS `CVDRisk` (`uuid` TEXT NOT NULL, `patientUuid` TEXT NOT NULL, `syncStatus` TEXT NOT NULL, `min` INTEGER NOT NULL, `max` INTEGER NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `deletedAt` TEXT, PRIMARY KEY(`uuid`))");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_CVDRisk_patientUuid` ON `CVDRisk` (`patientUuid`)");
                db.execSQL("CREATE VIEW `PatientSearchResult` AS SELECT P.uuid, P.fullName, P.gender, P.dateOfBirth, P.age_value, P.age_updatedAt,\n"
                        + "  P.assignedFacilityId, P.status, P.eligibleForReassignment,\n"
                        + "  PA.uuid addr_uuid, PA.streetAddress addr_streetAddress, PA.colonyOrVillage addr_colonyOrVillage, PA.zone addr_zone, PA.district addr_district,\n"
                        + "  PA.state addr_state, PA.country addr_country,\n"
                        + "  PA.createdAt addr_createdAt, PA.updatedAt addr_updatedAt, PA.deletedAt addr_deletedAt,\n"
                        + "  PP.number phoneNumber,\n"
                        + "  B.identifier id_identifier, B.identifierType id_identifierType, B.searchHelp identifierSearchHelp, AF.name assignedFacilityName\n"
                        + "  FROM Patient P\n"
                        + "  INNER JOIN PatientAddress PA ON PA.uuid = P.addressUuid\n"
                        + "  LEFT JOIN PatientPhoneNumber PP ON PP.patientUuid = P.uuid\n"
                        + "  LEFT JOIN Facility AF ON AF.uuid = P.assignedFacilityId\n"
                        + "  LEFT JOIN BusinessId B ON B.patientUuid = P.uuid\n"
                        + "  WHERE P.deletedAt IS NULL");
                db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7e42ccb8fd8c48bed0c6b7fb27fc4c56')");
            }

            @Override
            public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
                db.execSQL("DROP TABLE IF EXISTS `Patient`");
                db.execSQL("DROP TABLE IF EXISTS `PatientAddress`");
                db.execSQL("DROP TABLE IF EXISTS `PatientPhoneNumber`");
                db.execSQL("DROP TABLE IF EXISTS `BloodPressureMeasurement`");
                db.execSQL("DROP TABLE IF EXISTS `PrescribedDrug`");
                db.execSQL("DROP TABLE IF EXISTS `Facility`");
                db.execSQL("DROP TABLE IF EXISTS `LoggedInUser`");
                db.execSQL("DROP TABLE IF EXISTS `Appointment`");
                db.execSQL("DROP TABLE IF EXISTS `MedicalHistory`");
                db.execSQL("DROP TABLE IF EXISTS `OngoingLoginEntry`");
                db.execSQL("DROP TABLE IF EXISTS `Protocol`");
                db.execSQL("DROP TABLE IF EXISTS `ProtocolDrug`");
                db.execSQL("DROP TABLE IF EXISTS `BusinessId`");
                db.execSQL("DROP TABLE IF EXISTS `MissingPhoneReminder`");
                db.execSQL("DROP TABLE IF EXISTS `BloodSugarMeasurements`");
                db.execSQL("DROP TABLE IF EXISTS `TextRecords`");
                db.execSQL("DROP TABLE IF EXISTS `TeleconsultationFacilityInfo`");
                db.execSQL("DROP TABLE IF EXISTS `MedicalOfficer`");
                db.execSQL("DROP TABLE IF EXISTS `TeleconsultationFacilityMedicalOfficersCrossRef`");
                db.execSQL("DROP TABLE IF EXISTS `TeleconsultRecord`");
                db.execSQL("DROP TABLE IF EXISTS `Drug`");
                db.execSQL("DROP TABLE IF EXISTS `CallResult`");
                db.execSQL("DROP TABLE IF EXISTS `PatientFts`");
                db.execSQL("DROP TABLE IF EXISTS `PatientPhoneNumberFts`");
                db.execSQL("DROP TABLE IF EXISTS `BusinessIdFts`");
                db.execSQL("DROP TABLE IF EXISTS `PatientAddressFts`");
                db.execSQL("DROP TABLE IF EXISTS `Questionnaire`");
                db.execSQL("DROP TABLE IF EXISTS `QuestionnaireResponse`");
                db.execSQL("DROP TABLE IF EXISTS `PatientAttribute`");
                db.execSQL("DROP TABLE IF EXISTS `CVDRisk`");
                db.execSQL("DROP VIEW IF EXISTS `PatientSearchResult`");
                final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
                if (_callbacks != null) {
                    for (RoomDatabase.Callback _callback : _callbacks) {
                        _callback.onDestructiveMigration(db);
                    }
                }
            }

            @Override
            public void onCreate(@NonNull final SupportSQLiteDatabase db) {
                final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
                if (_callbacks != null) {
                    for (RoomDatabase.Callback _callback : _callbacks) {
                        _callback.onCreate(db);
                    }
                }
            }

            @Override
            public void onOpen(@NonNull final SupportSQLiteDatabase db) {
                mDatabase = db;
                db.execSQL("PRAGMA foreign_keys = ON");
                internalInitInvalidationTracker(db);
                final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
                if (_callbacks != null) {
                    for (RoomDatabase.Callback _callback : _callbacks) {
                        _callback.onOpen(db);
                    }
                }
            }

            @Override
            public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
                DBUtil.dropFtsSyncTriggers(db);
            }

            @Override
            public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientFts_BEFORE_UPDATE BEFORE UPDATE ON `Patient` BEGIN DELETE FROM `PatientFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientFts_BEFORE_DELETE BEFORE DELETE ON `Patient` BEGIN DELETE FROM `PatientFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientFts_AFTER_UPDATE AFTER UPDATE ON `Patient` BEGIN INSERT INTO `PatientFts`(`docid`, `uuid`, `fullName`) VALUES (NEW.`rowid`, NEW.`uuid`, NEW.`fullName`); END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientFts_AFTER_INSERT AFTER INSERT ON `Patient` BEGIN INSERT INTO `PatientFts`(`docid`, `uuid`, `fullName`) VALUES (NEW.`rowid`, NEW.`uuid`, NEW.`fullName`); END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientPhoneNumberFts_BEFORE_UPDATE BEFORE UPDATE ON `PatientPhoneNumber` BEGIN DELETE FROM `PatientPhoneNumberFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientPhoneNumberFts_BEFORE_DELETE BEFORE DELETE ON `PatientPhoneNumber` BEGIN DELETE FROM `PatientPhoneNumberFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientPhoneNumberFts_AFTER_UPDATE AFTER UPDATE ON `PatientPhoneNumber` BEGIN INSERT INTO `PatientPhoneNumberFts`(`docid`, `patientUuid`, `number`) VALUES (NEW.`rowid`, NEW.`patientUuid`, NEW.`number`); END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientPhoneNumberFts_AFTER_INSERT AFTER INSERT ON `PatientPhoneNumber` BEGIN INSERT INTO `PatientPhoneNumberFts`(`docid`, `patientUuid`, `number`) VALUES (NEW.`rowid`, NEW.`patientUuid`, NEW.`number`); END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_BusinessIdFts_BEFORE_UPDATE BEFORE UPDATE ON `BusinessId` BEGIN DELETE FROM `BusinessIdFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_BusinessIdFts_BEFORE_DELETE BEFORE DELETE ON `BusinessId` BEGIN DELETE FROM `BusinessIdFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_BusinessIdFts_AFTER_UPDATE AFTER UPDATE ON `BusinessId` BEGIN INSERT INTO `BusinessIdFts`(`docid`, `patientUuid`, `searchHelp`) VALUES (NEW.`rowid`, NEW.`patientUuid`, NEW.`searchHelp`); END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_BusinessIdFts_AFTER_INSERT AFTER INSERT ON `BusinessId` BEGIN INSERT INTO `BusinessIdFts`(`docid`, `patientUuid`, `searchHelp`) VALUES (NEW.`rowid`, NEW.`patientUuid`, NEW.`searchHelp`); END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientAddressFts_BEFORE_UPDATE BEFORE UPDATE ON `PatientAddress` BEGIN DELETE FROM `PatientAddressFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientAddressFts_BEFORE_DELETE BEFORE DELETE ON `PatientAddress` BEGIN DELETE FROM `PatientAddressFts` WHERE `docid`=OLD.`rowid`; END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientAddressFts_AFTER_UPDATE AFTER UPDATE ON `PatientAddress` BEGIN INSERT INTO `PatientAddressFts`(`docid`, `uuid`, `colonyOrVillage`) VALUES (NEW.`rowid`, NEW.`uuid`, NEW.`colonyOrVillage`); END");
                db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_PatientAddressFts_AFTER_INSERT AFTER INSERT ON `PatientAddress` BEGIN INSERT INTO `PatientAddressFts`(`docid`, `uuid`, `colonyOrVillage`) VALUES (NEW.`rowid`, NEW.`uuid`, NEW.`colonyOrVillage`); END");
            }

            @Override
            @NonNull
            public RoomOpenHelper.ValidationResult onValidateSchema(
                    @NonNull final SupportSQLiteDatabase db) {
                final HashMap<String, TableInfo.Column> _columnsPatient = new HashMap<String, TableInfo.Column>(19);
                _columnsPatient.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("addressUuid", new TableInfo.Column("addressUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("fullName", new TableInfo.Column("fullName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("gender", new TableInfo.Column("gender", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("recordedAt", new TableInfo.Column("recordedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("reminderConsent", new TableInfo.Column("reminderConsent", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("deletedReason", new TableInfo.Column("deletedReason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("registeredFacilityId", new TableInfo.Column("registeredFacilityId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("assignedFacilityId", new TableInfo.Column("assignedFacilityId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("retainUntil", new TableInfo.Column("retainUntil", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("eligibleForReassignment", new TableInfo.Column("eligibleForReassignment", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("age_value", new TableInfo.Column("age_value", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("age_updatedAt", new TableInfo.Column("age_updatedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatient.put("dateOfBirth", new TableInfo.Column("dateOfBirth", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysPatient = new HashSet<TableInfo.ForeignKey>(1);
                _foreignKeysPatient.add(new TableInfo.ForeignKey("PatientAddress", "CASCADE", "CASCADE", Arrays.asList("addressUuid"), Arrays.asList("uuid")));
                final HashSet<TableInfo.Index> _indicesPatient = new HashSet<TableInfo.Index>(2);
                _indicesPatient.add(new TableInfo.Index("index_Patient_addressUuid", false, Arrays.asList("addressUuid"), Arrays.asList("ASC")));
                _indicesPatient.add(new TableInfo.Index("index_Patient_assignedFacilityId", false, Arrays.asList("assignedFacilityId"), Arrays.asList("ASC")));
                final TableInfo _infoPatient = new TableInfo("Patient", _columnsPatient, _foreignKeysPatient, _indicesPatient);
                final TableInfo _existingPatient = TableInfo.read(db, "Patient");
                if (!_infoPatient.equals(_existingPatient)) {
                    return new RoomOpenHelper.ValidationResult(false, "Patient(org.simple.clinic.patient.Patient).\n"
                            + " Expected:\n" + _infoPatient + "\n"
                            + " Found:\n" + _existingPatient);
                }
                final HashMap<String, TableInfo.Column> _columnsPatientAddress = new HashMap<String, TableInfo.Column>(10);
                _columnsPatientAddress.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAddress.put("streetAddress", new TableInfo.Column("streetAddress", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAddress.put("colonyOrVillage", new TableInfo.Column("colonyOrVillage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAddress.put("zone", new TableInfo.Column("zone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAddress.put("district", new TableInfo.Column("district", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAddress.put("state", new TableInfo.Column("state", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAddress.put("country", new TableInfo.Column("country", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAddress.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAddress.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAddress.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysPatientAddress = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesPatientAddress = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoPatientAddress = new TableInfo("PatientAddress", _columnsPatientAddress, _foreignKeysPatientAddress, _indicesPatientAddress);
                final TableInfo _existingPatientAddress = TableInfo.read(db, "PatientAddress");
                if (!_infoPatientAddress.equals(_existingPatientAddress)) {
                    return new RoomOpenHelper.ValidationResult(false, "PatientAddress(org.simple.clinic.patient.PatientAddress).\n"
                            + " Expected:\n" + _infoPatientAddress + "\n"
                            + " Found:\n" + _existingPatientAddress);
                }
                final HashMap<String, TableInfo.Column> _columnsPatientPhoneNumber = new HashMap<String, TableInfo.Column>(8);
                _columnsPatientPhoneNumber.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientPhoneNumber.put("patientUuid", new TableInfo.Column("patientUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientPhoneNumber.put("number", new TableInfo.Column("number", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientPhoneNumber.put("phoneType", new TableInfo.Column("phoneType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientPhoneNumber.put("active", new TableInfo.Column("active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientPhoneNumber.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientPhoneNumber.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientPhoneNumber.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysPatientPhoneNumber = new HashSet<TableInfo.ForeignKey>(1);
                _foreignKeysPatientPhoneNumber.add(new TableInfo.ForeignKey("Patient", "CASCADE", "CASCADE", Arrays.asList("patientUuid"), Arrays.asList("uuid")));
                final HashSet<TableInfo.Index> _indicesPatientPhoneNumber = new HashSet<TableInfo.Index>(1);
                _indicesPatientPhoneNumber.add(new TableInfo.Index("index_PatientPhoneNumber_patientUuid", false, Arrays.asList("patientUuid"), Arrays.asList("ASC")));
                final TableInfo _infoPatientPhoneNumber = new TableInfo("PatientPhoneNumber", _columnsPatientPhoneNumber, _foreignKeysPatientPhoneNumber, _indicesPatientPhoneNumber);
                final TableInfo _existingPatientPhoneNumber = TableInfo.read(db, "PatientPhoneNumber");
                if (!_infoPatientPhoneNumber.equals(_existingPatientPhoneNumber)) {
                    return new RoomOpenHelper.ValidationResult(false, "PatientPhoneNumber(org.simple.clinic.patient.PatientPhoneNumber).\n"
                            + " Expected:\n" + _infoPatientPhoneNumber + "\n"
                            + " Found:\n" + _existingPatientPhoneNumber);
                }
                final HashMap<String, TableInfo.Column> _columnsBloodPressureMeasurement = new HashMap<String, TableInfo.Column>(11);
                _columnsBloodPressureMeasurement.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodPressureMeasurement.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodPressureMeasurement.put("userUuid", new TableInfo.Column("userUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodPressureMeasurement.put("facilityUuid", new TableInfo.Column("facilityUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodPressureMeasurement.put("patientUuid", new TableInfo.Column("patientUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodPressureMeasurement.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodPressureMeasurement.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodPressureMeasurement.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodPressureMeasurement.put("recordedAt", new TableInfo.Column("recordedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodPressureMeasurement.put("systolic", new TableInfo.Column("systolic", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodPressureMeasurement.put("diastolic", new TableInfo.Column("diastolic", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysBloodPressureMeasurement = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesBloodPressureMeasurement = new HashSet<TableInfo.Index>(2);
                _indicesBloodPressureMeasurement.add(new TableInfo.Index("index_BloodPressureMeasurement_patientUuid", false, Arrays.asList("patientUuid"), Arrays.asList("ASC")));
                _indicesBloodPressureMeasurement.add(new TableInfo.Index("index_BloodPressureMeasurement_facilityUuid", false, Arrays.asList("facilityUuid"), Arrays.asList("ASC")));
                final TableInfo _infoBloodPressureMeasurement = new TableInfo("BloodPressureMeasurement", _columnsBloodPressureMeasurement, _foreignKeysBloodPressureMeasurement, _indicesBloodPressureMeasurement);
                final TableInfo _existingBloodPressureMeasurement = TableInfo.read(db, "BloodPressureMeasurement");
                if (!_infoBloodPressureMeasurement.equals(_existingBloodPressureMeasurement)) {
                    return new RoomOpenHelper.ValidationResult(false, "BloodPressureMeasurement(org.simple.clinic.bp.BloodPressureMeasurement).\n"
                            + " Expected:\n" + _infoBloodPressureMeasurement + "\n"
                            + " Found:\n" + _existingBloodPressureMeasurement);
                }
                final HashMap<String, TableInfo.Column> _columnsPrescribedDrug = new HashMap<String, TableInfo.Column>(15);
                _columnsPrescribedDrug.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("dosage", new TableInfo.Column("dosage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("rxNormCode", new TableInfo.Column("rxNormCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("isProtocolDrug", new TableInfo.Column("isProtocolDrug", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("patientUuid", new TableInfo.Column("patientUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("facilityUuid", new TableInfo.Column("facilityUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("frequency", new TableInfo.Column("frequency", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("durationInDays", new TableInfo.Column("durationInDays", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("teleconsultationId", new TableInfo.Column("teleconsultationId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPrescribedDrug.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysPrescribedDrug = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesPrescribedDrug = new HashSet<TableInfo.Index>(2);
                _indicesPrescribedDrug.add(new TableInfo.Index("index_PrescribedDrug_patientUuid", false, Arrays.asList("patientUuid"), Arrays.asList("ASC")));
                _indicesPrescribedDrug.add(new TableInfo.Index("index_PrescribedDrug_facilityUuid", false, Arrays.asList("facilityUuid"), Arrays.asList("ASC")));
                final TableInfo _infoPrescribedDrug = new TableInfo("PrescribedDrug", _columnsPrescribedDrug, _foreignKeysPrescribedDrug, _indicesPrescribedDrug);
                final TableInfo _existingPrescribedDrug = TableInfo.read(db, "PrescribedDrug");
                if (!_infoPrescribedDrug.equals(_existingPrescribedDrug)) {
                    return new RoomOpenHelper.ValidationResult(false, "PrescribedDrug(org.simple.clinic.drugs.PrescribedDrug).\n"
                            + " Expected:\n" + _infoPrescribedDrug + "\n"
                            + " Found:\n" + _existingPrescribedDrug);
                }
                final HashMap<String, TableInfo.Column> _columnsFacility = new HashMap<String, TableInfo.Column>(22);
                _columnsFacility.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("facilityType", new TableInfo.Column("facilityType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("streetAddress", new TableInfo.Column("streetAddress", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("villageOrColony", new TableInfo.Column("villageOrColony", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("district", new TableInfo.Column("district", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("state", new TableInfo.Column("state", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("country", new TableInfo.Column("country", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("pinCode", new TableInfo.Column("pinCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("protocolUuid", new TableInfo.Column("protocolUuid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("groupUuid", new TableInfo.Column("groupUuid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("syncGroup", new TableInfo.Column("syncGroup", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("location_latitude", new TableInfo.Column("location_latitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("location_longitude", new TableInfo.Column("location_longitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("config_diabetesManagementEnabled", new TableInfo.Column("config_diabetesManagementEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("config_teleconsultationEnabled", new TableInfo.Column("config_teleconsultationEnabled", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("config_monthlyScreeningReportsEnabled", new TableInfo.Column("config_monthlyScreeningReportsEnabled", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsFacility.put("config_monthlySuppliesReportsEnabled", new TableInfo.Column("config_monthlySuppliesReportsEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysFacility = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesFacility = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoFacility = new TableInfo("Facility", _columnsFacility, _foreignKeysFacility, _indicesFacility);
                final TableInfo _existingFacility = TableInfo.read(db, "Facility");
                if (!_infoFacility.equals(_existingFacility)) {
                    return new RoomOpenHelper.ValidationResult(false, "Facility(org.simple.clinic.facility.Facility).\n"
                            + " Expected:\n" + _infoFacility + "\n"
                            + " Found:\n" + _existingFacility);
                }
                final HashMap<String, TableInfo.Column> _columnsLoggedInUser = new HashMap<String, TableInfo.Column>(12);
                _columnsLoggedInUser.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsLoggedInUser.put("fullName", new TableInfo.Column("fullName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsLoggedInUser.put("phoneNumber", new TableInfo.Column("phoneNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsLoggedInUser.put("pinDigest", new TableInfo.Column("pinDigest", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsLoggedInUser.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsLoggedInUser.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsLoggedInUser.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsLoggedInUser.put("loggedInStatus", new TableInfo.Column("loggedInStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsLoggedInUser.put("registrationFacilityUuid", new TableInfo.Column("registrationFacilityUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsLoggedInUser.put("currentFacilityUuid", new TableInfo.Column("currentFacilityUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsLoggedInUser.put("teleconsultPhoneNumber", new TableInfo.Column("teleconsultPhoneNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsLoggedInUser.put("capability_canTeleconsult", new TableInfo.Column("capability_canTeleconsult", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysLoggedInUser = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesLoggedInUser = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoLoggedInUser = new TableInfo("LoggedInUser", _columnsLoggedInUser, _foreignKeysLoggedInUser, _indicesLoggedInUser);
                final TableInfo _existingLoggedInUser = TableInfo.read(db, "LoggedInUser");
                if (!_infoLoggedInUser.equals(_existingLoggedInUser)) {
                    return new RoomOpenHelper.ValidationResult(false, "LoggedInUser(org.simple.clinic.user.User).\n"
                            + " Expected:\n" + _infoLoggedInUser + "\n"
                            + " Found:\n" + _existingLoggedInUser);
                }
                final HashMap<String, TableInfo.Column> _columnsAppointment = new HashMap<String, TableInfo.Column>(14);
                _columnsAppointment.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsAppointment.put("patientUuid", new TableInfo.Column("patientUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsAppointment.put("facilityUuid", new TableInfo.Column("facilityUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsAppointment.put("scheduledDate", new TableInfo.Column("scheduledDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsAppointment.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsAppointment.put("cancelReason", new TableInfo.Column("cancelReason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsAppointment.put("remindOn", new TableInfo.Column("remindOn", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsAppointment.put("agreedToVisit", new TableInfo.Column("agreedToVisit", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsAppointment.put("appointmentType", new TableInfo.Column("appointmentType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsAppointment.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsAppointment.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsAppointment.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsAppointment.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsAppointment.put("creationFacilityUuid", new TableInfo.Column("creationFacilityUuid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysAppointment = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesAppointment = new HashSet<TableInfo.Index>(3);
                _indicesAppointment.add(new TableInfo.Index("index_Appointment_patientUuid", false, Arrays.asList("patientUuid"), Arrays.asList("ASC")));
                _indicesAppointment.add(new TableInfo.Index("index_Appointment_creationFacilityUuid", false, Arrays.asList("creationFacilityUuid"), Arrays.asList("ASC")));
                _indicesAppointment.add(new TableInfo.Index("index_Appointment_facilityUuid", false, Arrays.asList("facilityUuid"), Arrays.asList("ASC")));
                final TableInfo _infoAppointment = new TableInfo("Appointment", _columnsAppointment, _foreignKeysAppointment, _indicesAppointment);
                final TableInfo _existingAppointment = TableInfo.read(db, "Appointment");
                if (!_infoAppointment.equals(_existingAppointment)) {
                    return new RoomOpenHelper.ValidationResult(false, "Appointment(org.simple.clinic.overdue.Appointment).\n"
                            + " Expected:\n" + _infoAppointment + "\n"
                            + " Found:\n" + _existingAppointment);
                }
                final HashMap<String, TableInfo.Column> _columnsMedicalHistory = new HashMap<String, TableInfo.Column>(15);
                _columnsMedicalHistory.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("patientUuid", new TableInfo.Column("patientUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("diagnosedWithHypertension", new TableInfo.Column("diagnosedWithHypertension", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("isOnHypertensionTreatment", new TableInfo.Column("isOnHypertensionTreatment", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("isOnDiabetesTreatment", new TableInfo.Column("isOnDiabetesTreatment", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("hasHadHeartAttack", new TableInfo.Column("hasHadHeartAttack", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("hasHadStroke", new TableInfo.Column("hasHadStroke", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("hasHadKidneyDisease", new TableInfo.Column("hasHadKidneyDisease", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("hasDiabetes", new TableInfo.Column("hasDiabetes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("isSmoking", new TableInfo.Column("isSmoking", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("cholesterol_value", new TableInfo.Column("cholesterol_value", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalHistory.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysMedicalHistory = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesMedicalHistory = new HashSet<TableInfo.Index>(1);
                _indicesMedicalHistory.add(new TableInfo.Index("index_MedicalHistory_patientUuid", false, Arrays.asList("patientUuid"), Arrays.asList("ASC")));
                final TableInfo _infoMedicalHistory = new TableInfo("MedicalHistory", _columnsMedicalHistory, _foreignKeysMedicalHistory, _indicesMedicalHistory);
                final TableInfo _existingMedicalHistory = TableInfo.read(db, "MedicalHistory");
                if (!_infoMedicalHistory.equals(_existingMedicalHistory)) {
                    return new RoomOpenHelper.ValidationResult(false, "MedicalHistory(org.simple.clinic.medicalhistory.MedicalHistory).\n"
                            + " Expected:\n" + _infoMedicalHistory + "\n"
                            + " Found:\n" + _existingMedicalHistory);
                }
                final HashMap<String, TableInfo.Column> _columnsOngoingLoginEntry = new HashMap<String, TableInfo.Column>(11);
                _columnsOngoingLoginEntry.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsOngoingLoginEntry.put("phoneNumber", new TableInfo.Column("phoneNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsOngoingLoginEntry.put("pin", new TableInfo.Column("pin", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsOngoingLoginEntry.put("fullName", new TableInfo.Column("fullName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsOngoingLoginEntry.put("pinDigest", new TableInfo.Column("pinDigest", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsOngoingLoginEntry.put("registrationFacilityUuid", new TableInfo.Column("registrationFacilityUuid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsOngoingLoginEntry.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsOngoingLoginEntry.put("createdAt", new TableInfo.Column("createdAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsOngoingLoginEntry.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsOngoingLoginEntry.put("teleconsultPhoneNumber", new TableInfo.Column("teleconsultPhoneNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsOngoingLoginEntry.put("capability_canTeleconsult", new TableInfo.Column("capability_canTeleconsult", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysOngoingLoginEntry = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesOngoingLoginEntry = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoOngoingLoginEntry = new TableInfo("OngoingLoginEntry", _columnsOngoingLoginEntry, _foreignKeysOngoingLoginEntry, _indicesOngoingLoginEntry);
                final TableInfo _existingOngoingLoginEntry = TableInfo.read(db, "OngoingLoginEntry");
                if (!_infoOngoingLoginEntry.equals(_existingOngoingLoginEntry)) {
                    return new RoomOpenHelper.ValidationResult(false, "OngoingLoginEntry(org.simple.clinic.user.OngoingLoginEntry).\n"
                            + " Expected:\n" + _infoOngoingLoginEntry + "\n"
                            + " Found:\n" + _existingOngoingLoginEntry);
                }
                final HashMap<String, TableInfo.Column> _columnsProtocol = new HashMap<String, TableInfo.Column>(7);
                _columnsProtocol.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocol.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocol.put("followUpDays", new TableInfo.Column("followUpDays", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocol.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocol.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocol.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocol.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysProtocol = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesProtocol = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoProtocol = new TableInfo("Protocol", _columnsProtocol, _foreignKeysProtocol, _indicesProtocol);
                final TableInfo _existingProtocol = TableInfo.read(db, "Protocol");
                if (!_infoProtocol.equals(_existingProtocol)) {
                    return new RoomOpenHelper.ValidationResult(false, "Protocol(org.simple.clinic.protocol.Protocol).\n"
                            + " Expected:\n" + _infoProtocol + "\n"
                            + " Found:\n" + _existingProtocol);
                }
                final HashMap<String, TableInfo.Column> _columnsProtocolDrug = new HashMap<String, TableInfo.Column>(9);
                _columnsProtocolDrug.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocolDrug.put("protocolUuid", new TableInfo.Column("protocolUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocolDrug.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocolDrug.put("rxNormCode", new TableInfo.Column("rxNormCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocolDrug.put("dosage", new TableInfo.Column("dosage", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocolDrug.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocolDrug.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocolDrug.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsProtocolDrug.put("order", new TableInfo.Column("order", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysProtocolDrug = new HashSet<TableInfo.ForeignKey>(1);
                _foreignKeysProtocolDrug.add(new TableInfo.ForeignKey("Protocol", "CASCADE", "NO ACTION", Arrays.asList("protocolUuid"), Arrays.asList("uuid")));
                final HashSet<TableInfo.Index> _indicesProtocolDrug = new HashSet<TableInfo.Index>(1);
                _indicesProtocolDrug.add(new TableInfo.Index("index_ProtocolDrug_protocolUuid", false, Arrays.asList("protocolUuid"), Arrays.asList("ASC")));
                final TableInfo _infoProtocolDrug = new TableInfo("ProtocolDrug", _columnsProtocolDrug, _foreignKeysProtocolDrug, _indicesProtocolDrug);
                final TableInfo _existingProtocolDrug = TableInfo.read(db, "ProtocolDrug");
                if (!_infoProtocolDrug.equals(_existingProtocolDrug)) {
                    return new RoomOpenHelper.ValidationResult(false, "ProtocolDrug(org.simple.clinic.protocol.ProtocolDrug).\n"
                            + " Expected:\n" + _infoProtocolDrug + "\n"
                            + " Found:\n" + _existingProtocolDrug);
                }
                final HashMap<String, TableInfo.Column> _columnsBusinessId = new HashMap<String, TableInfo.Column>(10);
                _columnsBusinessId.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBusinessId.put("patientUuid", new TableInfo.Column("patientUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBusinessId.put("metaVersion", new TableInfo.Column("metaVersion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBusinessId.put("meta", new TableInfo.Column("meta", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBusinessId.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBusinessId.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBusinessId.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBusinessId.put("searchHelp", new TableInfo.Column("searchHelp", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBusinessId.put("identifier", new TableInfo.Column("identifier", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBusinessId.put("identifierType", new TableInfo.Column("identifierType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysBusinessId = new HashSet<TableInfo.ForeignKey>(1);
                _foreignKeysBusinessId.add(new TableInfo.ForeignKey("Patient", "CASCADE", "NO ACTION", Arrays.asList("patientUuid"), Arrays.asList("uuid")));
                final HashSet<TableInfo.Index> _indicesBusinessId = new HashSet<TableInfo.Index>(2);
                _indicesBusinessId.add(new TableInfo.Index("index_BusinessId_patientUuid", false, Arrays.asList("patientUuid"), Arrays.asList("ASC")));
                _indicesBusinessId.add(new TableInfo.Index("index_BusinessId_identifier", false, Arrays.asList("identifier"), Arrays.asList("ASC")));
                final TableInfo _infoBusinessId = new TableInfo("BusinessId", _columnsBusinessId, _foreignKeysBusinessId, _indicesBusinessId);
                final TableInfo _existingBusinessId = TableInfo.read(db, "BusinessId");
                if (!_infoBusinessId.equals(_existingBusinessId)) {
                    return new RoomOpenHelper.ValidationResult(false, "BusinessId(org.simple.clinic.patient.businessid.BusinessId).\n"
                            + " Expected:\n" + _infoBusinessId + "\n"
                            + " Found:\n" + _existingBusinessId);
                }
                final HashMap<String, TableInfo.Column> _columnsMissingPhoneReminder = new HashMap<String, TableInfo.Column>(2);
                _columnsMissingPhoneReminder.put("patientUuid", new TableInfo.Column("patientUuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMissingPhoneReminder.put("remindedAt", new TableInfo.Column("remindedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysMissingPhoneReminder = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesMissingPhoneReminder = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoMissingPhoneReminder = new TableInfo("MissingPhoneReminder", _columnsMissingPhoneReminder, _foreignKeysMissingPhoneReminder, _indicesMissingPhoneReminder);
                final TableInfo _existingMissingPhoneReminder = TableInfo.read(db, "MissingPhoneReminder");
                if (!_infoMissingPhoneReminder.equals(_existingMissingPhoneReminder)) {
                    return new RoomOpenHelper.ValidationResult(false, "MissingPhoneReminder(org.simple.clinic.summary.addphone.MissingPhoneReminder).\n"
                            + " Expected:\n" + _infoMissingPhoneReminder + "\n"
                            + " Found:\n" + _existingMissingPhoneReminder);
                }
                final HashMap<String, TableInfo.Column> _columnsBloodSugarMeasurements = new HashMap<String, TableInfo.Column>(11);
                _columnsBloodSugarMeasurements.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodSugarMeasurements.put("recordedAt", new TableInfo.Column("recordedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodSugarMeasurements.put("patientUuid", new TableInfo.Column("patientUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodSugarMeasurements.put("userUuid", new TableInfo.Column("userUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodSugarMeasurements.put("facilityUuid", new TableInfo.Column("facilityUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodSugarMeasurements.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodSugarMeasurements.put("reading_value", new TableInfo.Column("reading_value", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodSugarMeasurements.put("reading_type", new TableInfo.Column("reading_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodSugarMeasurements.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodSugarMeasurements.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsBloodSugarMeasurements.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysBloodSugarMeasurements = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesBloodSugarMeasurements = new HashSet<TableInfo.Index>(2);
                _indicesBloodSugarMeasurements.add(new TableInfo.Index("index_BloodSugarMeasurements_patientUuid", false, Arrays.asList("patientUuid"), Arrays.asList("ASC")));
                _indicesBloodSugarMeasurements.add(new TableInfo.Index("index_BloodSugarMeasurements_facilityUuid", false, Arrays.asList("facilityUuid"), Arrays.asList("ASC")));
                final TableInfo _infoBloodSugarMeasurements = new TableInfo("BloodSugarMeasurements", _columnsBloodSugarMeasurements, _foreignKeysBloodSugarMeasurements, _indicesBloodSugarMeasurements);
                final TableInfo _existingBloodSugarMeasurements = TableInfo.read(db, "BloodSugarMeasurements");
                if (!_infoBloodSugarMeasurements.equals(_existingBloodSugarMeasurements)) {
                    return new RoomOpenHelper.ValidationResult(false, "BloodSugarMeasurements(org.simple.clinic.bloodsugar.BloodSugarMeasurement).\n"
                            + " Expected:\n" + _infoBloodSugarMeasurements + "\n"
                            + " Found:\n" + _existingBloodSugarMeasurements);
                }
                final HashMap<String, TableInfo.Column> _columnsTextRecords = new HashMap<String, TableInfo.Column>(2);
                _columnsTextRecords.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTextRecords.put("text", new TableInfo.Column("text", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysTextRecords = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesTextRecords = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoTextRecords = new TableInfo("TextRecords", _columnsTextRecords, _foreignKeysTextRecords, _indicesTextRecords);
                final TableInfo _existingTextRecords = TableInfo.read(db, "TextRecords");
                if (!_infoTextRecords.equals(_existingTextRecords)) {
                    return new RoomOpenHelper.ValidationResult(false, "TextRecords(org.simple.clinic.storage.text.TextRecord).\n"
                            + " Expected:\n" + _infoTextRecords + "\n"
                            + " Found:\n" + _existingTextRecords);
                }
                final HashMap<String, TableInfo.Column> _columnsTeleconsultationFacilityInfo = new HashMap<String, TableInfo.Column>(6);
                _columnsTeleconsultationFacilityInfo.put("teleconsultationFacilityId", new TableInfo.Column("teleconsultationFacilityId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultationFacilityInfo.put("facilityId", new TableInfo.Column("facilityId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultationFacilityInfo.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultationFacilityInfo.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultationFacilityInfo.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultationFacilityInfo.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysTeleconsultationFacilityInfo = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesTeleconsultationFacilityInfo = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoTeleconsultationFacilityInfo = new TableInfo("TeleconsultationFacilityInfo", _columnsTeleconsultationFacilityInfo, _foreignKeysTeleconsultationFacilityInfo, _indicesTeleconsultationFacilityInfo);
                final TableInfo _existingTeleconsultationFacilityInfo = TableInfo.read(db, "TeleconsultationFacilityInfo");
                if (!_infoTeleconsultationFacilityInfo.equals(_existingTeleconsultationFacilityInfo)) {
                    return new RoomOpenHelper.ValidationResult(false, "TeleconsultationFacilityInfo(org.simple.clinic.summary.teleconsultation.sync.TeleconsultationFacilityInfo).\n"
                            + " Expected:\n" + _infoTeleconsultationFacilityInfo + "\n"
                            + " Found:\n" + _existingTeleconsultationFacilityInfo);
                }
                final HashMap<String, TableInfo.Column> _columnsMedicalOfficer = new HashMap<String, TableInfo.Column>(3);
                _columnsMedicalOfficer.put("medicalOfficerId", new TableInfo.Column("medicalOfficerId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalOfficer.put("fullName", new TableInfo.Column("fullName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsMedicalOfficer.put("phoneNumber", new TableInfo.Column("phoneNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysMedicalOfficer = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesMedicalOfficer = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoMedicalOfficer = new TableInfo("MedicalOfficer", _columnsMedicalOfficer, _foreignKeysMedicalOfficer, _indicesMedicalOfficer);
                final TableInfo _existingMedicalOfficer = TableInfo.read(db, "MedicalOfficer");
                if (!_infoMedicalOfficer.equals(_existingMedicalOfficer)) {
                    return new RoomOpenHelper.ValidationResult(false, "MedicalOfficer(org.simple.clinic.summary.teleconsultation.sync.MedicalOfficer).\n"
                            + " Expected:\n" + _infoMedicalOfficer + "\n"
                            + " Found:\n" + _existingMedicalOfficer);
                }
                final HashMap<String, TableInfo.Column> _columnsTeleconsultationFacilityMedicalOfficersCrossRef = new HashMap<String, TableInfo.Column>(2);
                _columnsTeleconsultationFacilityMedicalOfficersCrossRef.put("teleconsultationFacilityId", new TableInfo.Column("teleconsultationFacilityId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultationFacilityMedicalOfficersCrossRef.put("medicalOfficerId", new TableInfo.Column("medicalOfficerId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysTeleconsultationFacilityMedicalOfficersCrossRef = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesTeleconsultationFacilityMedicalOfficersCrossRef = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoTeleconsultationFacilityMedicalOfficersCrossRef = new TableInfo("TeleconsultationFacilityMedicalOfficersCrossRef", _columnsTeleconsultationFacilityMedicalOfficersCrossRef, _foreignKeysTeleconsultationFacilityMedicalOfficersCrossRef, _indicesTeleconsultationFacilityMedicalOfficersCrossRef);
                final TableInfo _existingTeleconsultationFacilityMedicalOfficersCrossRef = TableInfo.read(db, "TeleconsultationFacilityMedicalOfficersCrossRef");
                if (!_infoTeleconsultationFacilityMedicalOfficersCrossRef.equals(_existingTeleconsultationFacilityMedicalOfficersCrossRef)) {
                    return new RoomOpenHelper.ValidationResult(false, "TeleconsultationFacilityMedicalOfficersCrossRef(org.simple.clinic.summary.teleconsultation.sync.TeleconsultationFacilityMedicalOfficersCrossRef).\n"
                            + " Expected:\n" + _infoTeleconsultationFacilityMedicalOfficersCrossRef + "\n"
                            + " Found:\n" + _existingTeleconsultationFacilityMedicalOfficersCrossRef);
                }
                final HashMap<String, TableInfo.Column> _columnsTeleconsultRecord = new HashMap<String, TableInfo.Column>(16);
                _columnsTeleconsultRecord.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("patientId", new TableInfo.Column("patientId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("medicalOfficerId", new TableInfo.Column("medicalOfficerId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("request_requesterId", new TableInfo.Column("request_requesterId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("request_facilityId", new TableInfo.Column("request_facilityId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("request_requestedAt", new TableInfo.Column("request_requestedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("request_requesterCompletionStatus", new TableInfo.Column("request_requesterCompletionStatus", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("record_recordedAt", new TableInfo.Column("record_recordedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("record_teleconsultationType", new TableInfo.Column("record_teleconsultationType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("record_patientTookMedicines", new TableInfo.Column("record_patientTookMedicines", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("record_patientConsented", new TableInfo.Column("record_patientConsented", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("record_medicalOfficerNumber", new TableInfo.Column("record_medicalOfficerNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsTeleconsultRecord.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysTeleconsultRecord = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesTeleconsultRecord = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoTeleconsultRecord = new TableInfo("TeleconsultRecord", _columnsTeleconsultRecord, _foreignKeysTeleconsultRecord, _indicesTeleconsultRecord);
                final TableInfo _existingTeleconsultRecord = TableInfo.read(db, "TeleconsultRecord");
                if (!_infoTeleconsultRecord.equals(_existingTeleconsultRecord)) {
                    return new RoomOpenHelper.ValidationResult(false, "TeleconsultRecord(org.simple.clinic.teleconsultlog.teleconsultrecord.TeleconsultRecord).\n"
                            + " Expected:\n" + _infoTeleconsultRecord + "\n"
                            + " Found:\n" + _existingTeleconsultRecord);
                }
                final HashMap<String, TableInfo.Column> _columnsDrug = new HashMap<String, TableInfo.Column>(12);
                _columnsDrug.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsDrug.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsDrug.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsDrug.put("frequency", new TableInfo.Column("frequency", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsDrug.put("composition", new TableInfo.Column("composition", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsDrug.put("dosage", new TableInfo.Column("dosage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsDrug.put("rxNormCode", new TableInfo.Column("rxNormCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsDrug.put("protocol", new TableInfo.Column("protocol", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsDrug.put("common", new TableInfo.Column("common", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsDrug.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsDrug.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsDrug.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysDrug = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesDrug = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoDrug = new TableInfo("Drug", _columnsDrug, _foreignKeysDrug, _indicesDrug);
                final TableInfo _existingDrug = TableInfo.read(db, "Drug");
                if (!_infoDrug.equals(_existingDrug)) {
                    return new RoomOpenHelper.ValidationResult(false, "Drug(org.simple.clinic.drugs.search.Drug).\n"
                            + " Expected:\n" + _infoDrug + "\n"
                            + " Found:\n" + _existingDrug);
                }
                final HashMap<String, TableInfo.Column> _columnsCallResult = new HashMap<String, TableInfo.Column>(11);
                _columnsCallResult.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCallResult.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCallResult.put("patientId", new TableInfo.Column("patientId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCallResult.put("facilityId", new TableInfo.Column("facilityId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCallResult.put("appointmentId", new TableInfo.Column("appointmentId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCallResult.put("removeReason", new TableInfo.Column("removeReason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCallResult.put("outcome", new TableInfo.Column("outcome", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCallResult.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCallResult.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCallResult.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCallResult.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysCallResult = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesCallResult = new HashSet<TableInfo.Index>(1);
                _indicesCallResult.add(new TableInfo.Index("index_CallResult_appointmentId", false, Arrays.asList("appointmentId"), Arrays.asList("ASC")));
                final TableInfo _infoCallResult = new TableInfo("CallResult", _columnsCallResult, _foreignKeysCallResult, _indicesCallResult);
                final TableInfo _existingCallResult = TableInfo.read(db, "CallResult");
                if (!_infoCallResult.equals(_existingCallResult)) {
                    return new RoomOpenHelper.ValidationResult(false, "CallResult(org.simple.clinic.overdue.callresult.CallResult).\n"
                            + " Expected:\n" + _infoCallResult + "\n"
                            + " Found:\n" + _existingCallResult);
                }
                final HashSet<String> _columnsPatientFts = new HashSet<String>(2);
                _columnsPatientFts.add("uuid");
                _columnsPatientFts.add("fullName");
                final FtsTableInfo _infoPatientFts = new FtsTableInfo("PatientFts", _columnsPatientFts, "CREATE VIRTUAL TABLE IF NOT EXISTS `PatientFts` USING FTS4(`uuid` TEXT NOT NULL, `fullName` TEXT NOT NULL, content=`Patient`)");
                final FtsTableInfo _existingPatientFts = FtsTableInfo.read(db, "PatientFts");
                if (!_infoPatientFts.equals(_existingPatientFts)) {
                    return new RoomOpenHelper.ValidationResult(false, "PatientFts(org.simple.clinic.patient.PatientFts).\n"
                            + " Expected:\n" + _infoPatientFts + "\n"
                            + " Found:\n" + _existingPatientFts);
                }
                final HashSet<String> _columnsPatientPhoneNumberFts = new HashSet<String>(2);
                _columnsPatientPhoneNumberFts.add("patientUuid");
                _columnsPatientPhoneNumberFts.add("number");
                final FtsTableInfo _infoPatientPhoneNumberFts = new FtsTableInfo("PatientPhoneNumberFts", _columnsPatientPhoneNumberFts, "CREATE VIRTUAL TABLE IF NOT EXISTS `PatientPhoneNumberFts` USING FTS4(`patientUuid` TEXT NOT NULL, `number` TEXT NOT NULL, content=`PatientPhoneNumber`)");
                final FtsTableInfo _existingPatientPhoneNumberFts = FtsTableInfo.read(db, "PatientPhoneNumberFts");
                if (!_infoPatientPhoneNumberFts.equals(_existingPatientPhoneNumberFts)) {
                    return new RoomOpenHelper.ValidationResult(false, "PatientPhoneNumberFts(org.simple.clinic.patient.PatientPhoneNumberFts).\n"
                            + " Expected:\n" + _infoPatientPhoneNumberFts + "\n"
                            + " Found:\n" + _existingPatientPhoneNumberFts);
                }
                final HashSet<String> _columnsBusinessIdFts = new HashSet<String>(2);
                _columnsBusinessIdFts.add("patientUuid");
                _columnsBusinessIdFts.add("searchHelp");
                final FtsTableInfo _infoBusinessIdFts = new FtsTableInfo("BusinessIdFts", _columnsBusinessIdFts, "CREATE VIRTUAL TABLE IF NOT EXISTS `BusinessIdFts` USING FTS4(`patientUuid` TEXT NOT NULL, `searchHelp` TEXT NOT NULL, content=`BusinessId`)");
                final FtsTableInfo _existingBusinessIdFts = FtsTableInfo.read(db, "BusinessIdFts");
                if (!_infoBusinessIdFts.equals(_existingBusinessIdFts)) {
                    return new RoomOpenHelper.ValidationResult(false, "BusinessIdFts(org.simple.clinic.patient.businessid.BusinessIdFts).\n"
                            + " Expected:\n" + _infoBusinessIdFts + "\n"
                            + " Found:\n" + _existingBusinessIdFts);
                }
                final HashSet<String> _columnsPatientAddressFts = new HashSet<String>(2);
                _columnsPatientAddressFts.add("uuid");
                _columnsPatientAddressFts.add("colonyOrVillage");
                final FtsTableInfo _infoPatientAddressFts = new FtsTableInfo("PatientAddressFts", _columnsPatientAddressFts, "CREATE VIRTUAL TABLE IF NOT EXISTS `PatientAddressFts` USING FTS4(`uuid` TEXT NOT NULL, `colonyOrVillage` TEXT, content=`PatientAddress`)");
                final FtsTableInfo _existingPatientAddressFts = FtsTableInfo.read(db, "PatientAddressFts");
                if (!_infoPatientAddressFts.equals(_existingPatientAddressFts)) {
                    return new RoomOpenHelper.ValidationResult(false, "PatientAddressFts(org.simple.clinic.patient.PatientAddressFts).\n"
                            + " Expected:\n" + _infoPatientAddressFts + "\n"
                            + " Found:\n" + _existingPatientAddressFts);
                }
                final HashMap<String, TableInfo.Column> _columnsQuestionnaire = new HashMap<String, TableInfo.Column>(4);
                _columnsQuestionnaire.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsQuestionnaire.put("questionnaire_type", new TableInfo.Column("questionnaire_type", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsQuestionnaire.put("layout", new TableInfo.Column("layout", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsQuestionnaire.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysQuestionnaire = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesQuestionnaire = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoQuestionnaire = new TableInfo("Questionnaire", _columnsQuestionnaire, _foreignKeysQuestionnaire, _indicesQuestionnaire);
                final TableInfo _existingQuestionnaire = TableInfo.read(db, "Questionnaire");
                if (!_infoQuestionnaire.equals(_existingQuestionnaire)) {
                    return new RoomOpenHelper.ValidationResult(false, "Questionnaire(org.simple.clinic.questionnaire.Questionnaire).\n"
                            + " Expected:\n" + _infoQuestionnaire + "\n"
                            + " Found:\n" + _existingQuestionnaire);
                }
                final HashMap<String, TableInfo.Column> _columnsQuestionnaireResponse = new HashMap<String, TableInfo.Column>(10);
                _columnsQuestionnaireResponse.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsQuestionnaireResponse.put("questionnaireId", new TableInfo.Column("questionnaireId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsQuestionnaireResponse.put("questionnaireType", new TableInfo.Column("questionnaireType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsQuestionnaireResponse.put("facilityId", new TableInfo.Column("facilityId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsQuestionnaireResponse.put("lastUpdatedByUserId", new TableInfo.Column("lastUpdatedByUserId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsQuestionnaireResponse.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsQuestionnaireResponse.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsQuestionnaireResponse.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsQuestionnaireResponse.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsQuestionnaireResponse.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysQuestionnaireResponse = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesQuestionnaireResponse = new HashSet<TableInfo.Index>(0);
                final TableInfo _infoQuestionnaireResponse = new TableInfo("QuestionnaireResponse", _columnsQuestionnaireResponse, _foreignKeysQuestionnaireResponse, _indicesQuestionnaireResponse);
                final TableInfo _existingQuestionnaireResponse = TableInfo.read(db, "QuestionnaireResponse");
                if (!_infoQuestionnaireResponse.equals(_existingQuestionnaireResponse)) {
                    return new RoomOpenHelper.ValidationResult(false, "QuestionnaireResponse(org.simple.clinic.questionnaireresponse.QuestionnaireResponse).\n"
                            + " Expected:\n" + _infoQuestionnaireResponse + "\n"
                            + " Found:\n" + _existingQuestionnaireResponse);
                }
                final HashMap<String, TableInfo.Column> _columnsPatientAttribute = new HashMap<String, TableInfo.Column>(9);
                _columnsPatientAttribute.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAttribute.put("patientUuid", new TableInfo.Column("patientUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAttribute.put("userUuid", new TableInfo.Column("userUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAttribute.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAttribute.put("height", new TableInfo.Column("height", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAttribute.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAttribute.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAttribute.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsPatientAttribute.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysPatientAttribute = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesPatientAttribute = new HashSet<TableInfo.Index>(1);
                _indicesPatientAttribute.add(new TableInfo.Index("index_PatientAttribute_patientUuid", false, Arrays.asList("patientUuid"), Arrays.asList("ASC")));
                final TableInfo _infoPatientAttribute = new TableInfo("PatientAttribute", _columnsPatientAttribute, _foreignKeysPatientAttribute, _indicesPatientAttribute);
                final TableInfo _existingPatientAttribute = TableInfo.read(db, "PatientAttribute");
                if (!_infoPatientAttribute.equals(_existingPatientAttribute)) {
                    return new RoomOpenHelper.ValidationResult(false, "PatientAttribute(org.simple.clinic.patientattribute.PatientAttribute).\n"
                            + " Expected:\n" + _infoPatientAttribute + "\n"
                            + " Found:\n" + _existingPatientAttribute);
                }
                final HashMap<String, TableInfo.Column> _columnsCVDRisk = new HashMap<String, TableInfo.Column>(8);
                _columnsCVDRisk.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCVDRisk.put("patientUuid", new TableInfo.Column("patientUuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCVDRisk.put("syncStatus", new TableInfo.Column("syncStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCVDRisk.put("min", new TableInfo.Column("min", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCVDRisk.put("max", new TableInfo.Column("max", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCVDRisk.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCVDRisk.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
                _columnsCVDRisk.put("deletedAt", new TableInfo.Column("deletedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
                final HashSet<TableInfo.ForeignKey> _foreignKeysCVDRisk = new HashSet<TableInfo.ForeignKey>(0);
                final HashSet<TableInfo.Index> _indicesCVDRisk = new HashSet<TableInfo.Index>(1);
                _indicesCVDRisk.add(new TableInfo.Index("index_CVDRisk_patientUuid", false, Arrays.asList("patientUuid"), Arrays.asList("ASC")));
                final TableInfo _infoCVDRisk = new TableInfo("CVDRisk", _columnsCVDRisk, _foreignKeysCVDRisk, _indicesCVDRisk);
                final TableInfo _existingCVDRisk = TableInfo.read(db, "CVDRisk");
                if (!_infoCVDRisk.equals(_existingCVDRisk)) {
                    return new RoomOpenHelper.ValidationResult(false, "CVDRisk(org.simple.clinic.cvdrisk.CVDRisk).\n"
                            + " Expected:\n" + _infoCVDRisk + "\n"
                            + " Found:\n" + _existingCVDRisk);
                }
                final ViewInfo _infoPatientSearchResult = new ViewInfo("PatientSearchResult", "CREATE VIEW `PatientSearchResult` AS SELECT P.uuid, P.fullName, P.gender, P.dateOfBirth, P.age_value, P.age_updatedAt,\n"
                        + "  P.assignedFacilityId, P.status, P.eligibleForReassignment,\n"
                        + "  PA.uuid addr_uuid, PA.streetAddress addr_streetAddress, PA.colonyOrVillage addr_colonyOrVillage, PA.zone addr_zone, PA.district addr_district,\n"
                        + "  PA.state addr_state, PA.country addr_country,\n"
                        + "  PA.createdAt addr_createdAt, PA.updatedAt addr_updatedAt, PA.deletedAt addr_deletedAt,\n"
                        + "  PP.number phoneNumber,\n"
                        + "  B.identifier id_identifier, B.identifierType id_identifierType, B.searchHelp identifierSearchHelp, AF.name assignedFacilityName\n"
                        + "  FROM Patient P\n"
                        + "  INNER JOIN PatientAddress PA ON PA.uuid = P.addressUuid\n"
                        + "  LEFT JOIN PatientPhoneNumber PP ON PP.patientUuid = P.uuid\n"
                        + "  LEFT JOIN Facility AF ON AF.uuid = P.assignedFacilityId\n"
                        + "  LEFT JOIN BusinessId B ON B.patientUuid = P.uuid\n"
                        + "  WHERE P.deletedAt IS NULL");
                final ViewInfo _existingPatientSearchResult = ViewInfo.read(db, "PatientSearchResult");
                if (!_infoPatientSearchResult.equals(_existingPatientSearchResult)) {
                    return new RoomOpenHelper.ValidationResult(false, "PatientSearchResult(org.simple.clinic.patient.PatientSearchResult).\n"
                            + " Expected:\n" + _infoPatientSearchResult + "\n"
                            + " Found:\n" + _existingPatientSearchResult);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "7e42ccb8fd8c48bed0c6b7fb27fc4c56", "5134b46dad404099260628280eb4832f");
        final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
        final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
        return _helper;
    }

    @Override
    @NonNull
    protected InvalidationTracker createInvalidationTracker() {
        final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(4);
        _shadowTablesMap.put("PatientFts", "Patient");
        _shadowTablesMap.put("PatientPhoneNumberFts", "PatientPhoneNumber");
        _shadowTablesMap.put("BusinessIdFts", "BusinessId");
        _shadowTablesMap.put("PatientAddressFts", "PatientAddress");
        final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(1);
        final HashSet<String> _tables = new HashSet<String>(5);
        _tables.add("Patient");
        _tables.add("PatientAddress");
        _tables.add("PatientPhoneNumber");
        _tables.add("Facility");
        _tables.add("BusinessId");
        _viewTables.put("patientsearchresult", _tables);
        return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Patient","PatientAddress","PatientPhoneNumber","BloodPressureMeasurement","PrescribedDrug","Facility","LoggedInUser","Appointment","MedicalHistory","OngoingLoginEntry","Protocol","ProtocolDrug","BusinessId","MissingPhoneReminder","BloodSugarMeasurements","TextRecords","TeleconsultationFacilityInfo","MedicalOfficer","TeleconsultationFacilityMedicalOfficersCrossRef","TeleconsultRecord","Drug","CallResult","PatientFts","PatientPhoneNumberFts","BusinessIdFts","PatientAddressFts","Questionnaire","QuestionnaireResponse","PatientAttribute","CVDRisk");
    }

    @Override
    public void clearAllTables() {
        super.assertNotMainThread();
        final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
        final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
        try {
            if (!_supportsDeferForeignKeys) {
                _db.execSQL("PRAGMA foreign_keys = FALSE");
            }
            super.beginTransaction();
            if (_supportsDeferForeignKeys) {
                _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
            }
            _db.execSQL("DELETE FROM `Patient`");
            _db.execSQL("DELETE FROM `PatientAddress`");
            _db.execSQL("DELETE FROM `PatientPhoneNumber`");
            _db.execSQL("DELETE FROM `BloodPressureMeasurement`");
            _db.execSQL("DELETE FROM `PrescribedDrug`");
            _db.execSQL("DELETE FROM `Facility`");
            _db.execSQL("DELETE FROM `LoggedInUser`");
            _db.execSQL("DELETE FROM `Appointment`");
            _db.execSQL("DELETE FROM `MedicalHistory`");
            _db.execSQL("DELETE FROM `OngoingLoginEntry`");
            _db.execSQL("DELETE FROM `Protocol`");
            _db.execSQL("DELETE FROM `ProtocolDrug`");
            _db.execSQL("DELETE FROM `BusinessId`");
            _db.execSQL("DELETE FROM `MissingPhoneReminder`");
            _db.execSQL("DELETE FROM `BloodSugarMeasurements`");
            _db.execSQL("DELETE FROM `TextRecords`");
            _db.execSQL("DELETE FROM `TeleconsultationFacilityInfo`");
            _db.execSQL("DELETE FROM `MedicalOfficer`");
            _db.execSQL("DELETE FROM `TeleconsultationFacilityMedicalOfficersCrossRef`");
            _db.execSQL("DELETE FROM `TeleconsultRecord`");
            _db.execSQL("DELETE FROM `Drug`");
            _db.execSQL("DELETE FROM `CallResult`");
            _db.execSQL("DELETE FROM `PatientFts`");
            _db.execSQL("DELETE FROM `PatientPhoneNumberFts`");
            _db.execSQL("DELETE FROM `BusinessIdFts`");
            _db.execSQL("DELETE FROM `PatientAddressFts`");
            _db.execSQL("DELETE FROM `Questionnaire`");
            _db.execSQL("DELETE FROM `QuestionnaireResponse`");
            _db.execSQL("DELETE FROM `PatientAttribute`");
            _db.execSQL("DELETE FROM `CVDRisk`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            if (!_supportsDeferForeignKeys) {
                _db.execSQL("PRAGMA foreign_keys = TRUE");
            }
            _db.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!_db.inTransaction()) {
                _db.execSQL("VACUUM");
            }
        }
    }

    @Override
    @NonNull
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
        _typeConvertersMap.put(Patient.RoomDao.class, Patient_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(PatientAddress.RoomDao.class, PatientAddress_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(PatientPhoneNumber.RoomDao.class, PatientPhoneNumber_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(PatientSearchResult.RoomDao.class, PatientSearchResult_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(BloodPressureMeasurement.RoomDao.class, BloodPressureMeasurement_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(PrescribedDrug.RoomDao.class, PrescribedDrug_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(Facility.RoomDao.class, Facility_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(User.RoomDao.class, User_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(Appointment.RoomDao.class, Appointment_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(OverdueAppointment.RoomDao.class, OverdueAppointment_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(MedicalHistory.RoomDao.class, MedicalHistory_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(OngoingLoginEntry.RoomDao.class, OngoingLoginEntry_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(Protocol.RoomDao.class, Protocol_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(ProtocolDrug.RoomDao.class, ProtocolDrug_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(RecentPatient.RoomDao.class, RecentPatient_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(BusinessId.RoomDao.class, BusinessId_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(MissingPhoneReminder.RoomDao.class, MissingPhoneReminder_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(BloodSugarMeasurement.RoomDao.class, BloodSugarMeasurement_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(TextRecord.RoomDao.class, TextRecord_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(TeleconsultationFacilityInfo.RoomDao.class, TeleconsultationFacilityInfo_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(MedicalOfficer.RoomDao.class, MedicalOfficer_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(TeleconsultationFacilityWithMedicalOfficers.RoomDao.class, TeleconsultationFacilityWithMedicalOfficers_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(TeleconsultRecord.RoomDao.class, TeleconsultRecord_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(Drug.RoomDao.class, Drug_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(CallResult.RoomDao.class, CallResult_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(Questionnaire.RoomDao.class, Questionnaire_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(QuestionnaireResponse.RoomDao.class, QuestionnaireResponse_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(PatientAttribute.RoomDao.class, PatientAttribute_RoomDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(CVDRisk.RoomDao.class, CVDRisk_RoomDao_Impl.getRequiredConverters());
        return _typeConvertersMap;
    }

    @Override
    @NonNull
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
        return _autoMigrationSpecsSet;
    }

    @Override
    @NonNull
    public List<Migration> getAutoMigrations(
            @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
        final List<Migration> _autoMigrations = new ArrayList<Migration>();
        return _autoMigrations;
    }

    @Override
    public Patient.RoomDao patientDao() {
        if (_patient != null) {
            return _patient;
        } else {
            synchronized(this) {
                if(_patient == null) {
                    _patient = new Patient_RoomDao_Impl(this);
                }
                return _patient;
            }
        }
    }

    @Override
    public PatientAddress.RoomDao addressDao() {
        if (_patientAddress != null) {
            return _patientAddress;
        } else {
            synchronized(this) {
                if(_patientAddress == null) {
                    _patientAddress = new PatientAddress_RoomDao_Impl(this);
                }
                return _patientAddress;
            }
        }
    }

    @Override
    public PatientPhoneNumber.RoomDao phoneNumberDao() {
        if (_patientPhoneNumber != null) {
            return _patientPhoneNumber;
        } else {
            synchronized(this) {
                if(_patientPhoneNumber == null) {
                    _patientPhoneNumber = new PatientPhoneNumber_RoomDao_Impl(this);
                }
                return _patientPhoneNumber;
            }
        }
    }

    @Override
    public PatientSearchResult.RoomDao patientSearchDao() {
        if (_patientSearchResult != null) {
            return _patientSearchResult;
        } else {
            synchronized(this) {
                if(_patientSearchResult == null) {
                    _patientSearchResult = new PatientSearchResult_RoomDao_Impl(this);
                }
                return _patientSearchResult;
            }
        }
    }

    @Override
    public BloodPressureMeasurement.RoomDao bloodPressureDao() {
        if (_bloodPressureMeasurement != null) {
            return _bloodPressureMeasurement;
        } else {
            synchronized(this) {
                if(_bloodPressureMeasurement == null) {
                    _bloodPressureMeasurement = new BloodPressureMeasurement_RoomDao_Impl(this);
                }
                return _bloodPressureMeasurement;
            }
        }
    }

    @Override
    public PrescribedDrug.RoomDao prescriptionDao() {
        if (_prescribedDrug != null) {
            return _prescribedDrug;
        } else {
            synchronized(this) {
                if(_prescribedDrug == null) {
                    _prescribedDrug = new PrescribedDrug_RoomDao_Impl(this);
                }
                return _prescribedDrug;
            }
        }
    }

    @Override
    public Facility.RoomDao facilityDao() {
        if (_facility != null) {
            return _facility;
        } else {
            synchronized(this) {
                if(_facility == null) {
                    _facility = new Facility_RoomDao_Impl(this);
                }
                return _facility;
            }
        }
    }

    @Override
    public User.RoomDao userDao() {
        if (_user != null) {
            return _user;
        } else {
            synchronized(this) {
                if(_user == null) {
                    _user = new User_RoomDao_Impl(this);
                }
                return _user;
            }
        }
    }

    @Override
    public Appointment.RoomDao appointmentDao() {
        if (_appointment != null) {
            return _appointment;
        } else {
            synchronized(this) {
                if(_appointment == null) {
                    _appointment = new Appointment_RoomDao_Impl(this);
                }
                return _appointment;
            }
        }
    }

    @Override
    public OverdueAppointment.RoomDao overdueAppointmentDao() {
        if (_overdueAppointment != null) {
            return _overdueAppointment;
        } else {
            synchronized(this) {
                if(_overdueAppointment == null) {
                    _overdueAppointment = new OverdueAppointment_RoomDao_Impl(this);
                }
                return _overdueAppointment;
            }
        }
    }

    @Override
    public MedicalHistory.RoomDao medicalHistoryDao() {
        if (_medicalHistory != null) {
            return _medicalHistory;
        } else {
            synchronized(this) {
                if(_medicalHistory == null) {
                    _medicalHistory = new MedicalHistory_RoomDao_Impl(this);
                }
                return _medicalHistory;
            }
        }
    }

    @Override
    public OngoingLoginEntry.RoomDao ongoingLoginEntryDao() {
        if (_ongoingLoginEntry != null) {
            return _ongoingLoginEntry;
        } else {
            synchronized(this) {
                if(_ongoingLoginEntry == null) {
                    _ongoingLoginEntry = new OngoingLoginEntry_RoomDao_Impl(this);
                }
                return _ongoingLoginEntry;
            }
        }
    }

    @Override
    public Protocol.RoomDao protocolDao() {
        if (_protocol != null) {
            return _protocol;
        } else {
            synchronized(this) {
                if(_protocol == null) {
                    _protocol = new Protocol_RoomDao_Impl(this);
                }
                return _protocol;
            }
        }
    }

    @Override
    public ProtocolDrug.RoomDao protocolDrugDao() {
        if (_protocolDrug != null) {
            return _protocolDrug;
        } else {
            synchronized(this) {
                if(_protocolDrug == null) {
                    _protocolDrug = new ProtocolDrug_RoomDao_Impl(this);
                }
                return _protocolDrug;
            }
        }
    }

    @Override
    public RecentPatient.RoomDao recentPatientDao() {
        if (_recentPatient != null) {
            return _recentPatient;
        } else {
            synchronized(this) {
                if(_recentPatient == null) {
                    _recentPatient = new RecentPatient_RoomDao_Impl(this);
                }
                return _recentPatient;
            }
        }
    }

    @Override
    public BusinessId.RoomDao businessIdDao() {
        if (_businessId != null) {
            return _businessId;
        } else {
            synchronized(this) {
                if(_businessId == null) {
                    _businessId = new BusinessId_RoomDao_Impl(this);
                }
                return _businessId;
            }
        }
    }

    @Override
    public MissingPhoneReminder.RoomDao missingPhoneReminderDao() {
        if (_missingPhoneReminder != null) {
            return _missingPhoneReminder;
        } else {
            synchronized(this) {
                if(_missingPhoneReminder == null) {
                    _missingPhoneReminder = new MissingPhoneReminder_RoomDao_Impl(this);
                }
                return _missingPhoneReminder;
            }
        }
    }

    @Override
    public BloodSugarMeasurement.RoomDao bloodSugarDao() {
        if (_bloodSugarMeasurement != null) {
            return _bloodSugarMeasurement;
        } else {
            synchronized(this) {
                if(_bloodSugarMeasurement == null) {
                    _bloodSugarMeasurement = new BloodSugarMeasurement_RoomDao_Impl(this);
                }
                return _bloodSugarMeasurement;
            }
        }
    }

    @Override
    public TextRecord.RoomDao textRecordDao() {
        if (_textRecord != null) {
            return _textRecord;
        } else {
            synchronized(this) {
                if(_textRecord == null) {
                    _textRecord = new TextRecord_RoomDao_Impl(this);
                }
                return _textRecord;
            }
        }
    }

    @Override
    public TeleconsultationFacilityInfo.RoomDao teleconsultFacilityInfoDao() {
        if (_teleconsultationFacilityInfo != null) {
            return _teleconsultationFacilityInfo;
        } else {
            synchronized(this) {
                if(_teleconsultationFacilityInfo == null) {
                    _teleconsultationFacilityInfo = new TeleconsultationFacilityInfo_RoomDao_Impl(this);
                }
                return _teleconsultationFacilityInfo;
            }
        }
    }

    @Override
    public MedicalOfficer.RoomDao teleconsultMedicalOfficersDao() {
        if (_medicalOfficer != null) {
            return _medicalOfficer;
        } else {
            synchronized(this) {
                if(_medicalOfficer == null) {
                    _medicalOfficer = new MedicalOfficer_RoomDao_Impl(this);
                }
                return _medicalOfficer;
            }
        }
    }

    @Override
    public TeleconsultationFacilityWithMedicalOfficers.RoomDao teleconsultFacilityWithMedicalOfficersDao(
    ) {
        if (_teleconsultationFacilityWithMedicalOfficers != null) {
            return _teleconsultationFacilityWithMedicalOfficers;
        } else {
            synchronized(this) {
                if(_teleconsultationFacilityWithMedicalOfficers == null) {
                    _teleconsultationFacilityWithMedicalOfficers = new TeleconsultationFacilityWithMedicalOfficers_RoomDao_Impl(this);
                }
                return _teleconsultationFacilityWithMedicalOfficers;
            }
        }
    }

    @Override
    public TeleconsultRecord.RoomDao teleconsultRecordDao() {
        if (_teleconsultRecord != null) {
            return _teleconsultRecord;
        } else {
            synchronized(this) {
                if(_teleconsultRecord == null) {
                    _teleconsultRecord = new TeleconsultRecord_RoomDao_Impl(this);
                }
                return _teleconsultRecord;
            }
        }
    }

    @Override
    public Drug.RoomDao drugDao() {
        if (_drug != null) {
            return _drug;
        } else {
            synchronized(this) {
                if(_drug == null) {
                    _drug = new Drug_RoomDao_Impl(this);
                }
                return _drug;
            }
        }
    }

    @Override
    public CallResult.RoomDao callResultDao() {
        if (_callResult != null) {
            return _callResult;
        } else {
            synchronized(this) {
                if(_callResult == null) {
                    _callResult = new CallResult_RoomDao_Impl(this);
                }
                return _callResult;
            }
        }
    }

    @Override
    public Questionnaire.RoomDao questionnaireDao() {
        if (_questionnaire != null) {
            return _questionnaire;
        } else {
            synchronized(this) {
                if(_questionnaire == null) {
                    _questionnaire = new Questionnaire_RoomDao_Impl(this);
                }
                return _questionnaire;
            }
        }
    }

    @Override
    public QuestionnaireResponse.RoomDao questionnaireResponseDao() {
        if (_questionnaireResponse != null) {
            return _questionnaireResponse;
        } else {
            synchronized(this) {
                if(_questionnaireResponse == null) {
                    _questionnaireResponse = new QuestionnaireResponse_RoomDao_Impl(this);
                }
                return _questionnaireResponse;
            }
        }
    }

    @Override
    public PatientAttribute.RoomDao patientAttributeDao() {
        if (_patientAttribute != null) {
            return _patientAttribute;
        } else {
            synchronized(this) {
                if(_patientAttribute == null) {
                    _patientAttribute = new PatientAttribute_RoomDao_Impl(this);
                }
                return _patientAttribute;
            }
        }
    }

    @Override
    public CVDRisk.RoomDao cvdRiskDao() {
        if (_cVDRisk != null) {
            return _cVDRisk;
        } else {
            synchronized(this) {
                if(_cVDRisk == null) {
                    _cVDRisk = new CVDRisk_RoomDao_Impl(this);
                }
                return _cVDRisk;
            }
        }
    }
}
