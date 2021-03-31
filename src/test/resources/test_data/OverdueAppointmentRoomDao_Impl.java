package test_data;

import android.database.Cursor;
import androidx.paging.DataSource;
import androidx.paging.DataSource.Factory;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import io.reactivex.Observable;
import java.lang.Boolean;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.simple.clinic.medicalhistory.Answer;
import org.simple.clinic.overdue.Appointment;
import org.simple.clinic.overdue.AppointmentCancelReason;
import org.simple.clinic.patient.Age;
import org.simple.clinic.patient.Gender;
import org.simple.clinic.patient.PatientPhoneNumber;
import org.simple.clinic.patient.PatientPhoneNumberType;
import org.simple.clinic.patient.SyncStatus;
import org.simple.clinic.util.room.InstantRoomTypeConverter;
import org.simple.clinic.util.room.LocalDateRoomTypeConverter;
import org.simple.clinic.util.room.UuidRoomTypeConverter;

@SuppressWarnings({"unchecked", "deprecation"})
public final class OverdueAppointmentRoomDao_Impl implements OverdueAppointment.RoomDao {
  private final RoomDatabase __db;

  private final UuidRoomTypeConverter __uuidRoomTypeConverter = new UuidRoomTypeConverter();

  private final LocalDateRoomTypeConverter __localDateRoomTypeConverter = new LocalDateRoomTypeConverter();

  private final Gender.RoomTypeConverter __roomTypeConverter = new Gender.RoomTypeConverter();

  private final Answer.RoomTypeConverter __roomTypeConverter_1 = new Answer.RoomTypeConverter();

  private final InstantRoomTypeConverter __instantRoomTypeConverter = new InstantRoomTypeConverter();

  private final Appointment.Status.RoomTypeConverter __roomTypeConverter_2 = new Appointment.Status.RoomTypeConverter();

  private final AppointmentCancelReason.RoomTypeConverter __roomTypeConverter_3 = new AppointmentCancelReason.RoomTypeConverter();

  private final Appointment.AppointmentType.RoomTypeConverter __roomTypeConverter_4 = new Appointment.AppointmentType.RoomTypeConverter();

  private final SyncStatus.RoomTypeConverter __roomTypeConverter_5 = new SyncStatus.RoomTypeConverter();

  private final PatientPhoneNumberType.RoomTypeConverter __roomTypeConverter_6 = new PatientPhoneNumberType.RoomTypeConverter();

  public OverdueAppointmentRoomDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public Observable<List<OverdueAppointment>> overdueAtFacility(final UUID facilityUuid,
      final LocalDate scheduledBefore, final LocalDate scheduledAfter) {
    final String _sql = "\n"
            + "      SELECT * FROM OverdueAppointment\n"
            + "      WHERE \n"
            + "        IFNULL(patientAssignedFacilityUuid, appt_facilityUuid) = ? \n"
            + "        AND (appt_scheduledDate < ? AND appt_scheduledDate > ?)\n"
            + "        AND (appt_remindOn < ? OR appt_remindOn IS NULL)\n"
            + "        GROUP BY appt_patientUuid\n"
            + "        ORDER BY isAtHighRisk DESC, appt_scheduledDate DESC, appt_updatedAt ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __uuidRoomTypeConverter.fromUuid(facilityUuid);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    _argIndex = 2;
    final String _tmp_1;
    _tmp_1 = __localDateRoomTypeConverter.fromLocalDate(scheduledBefore);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_1);
    }
    _argIndex = 3;
    final String _tmp_2;
    _tmp_2 = __localDateRoomTypeConverter.fromLocalDate(scheduledAfter);
    if (_tmp_2 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_2);
    }
    _argIndex = 4;
    final String _tmp_3;
    _tmp_3 = __localDateRoomTypeConverter.fromLocalDate(scheduledBefore);
    if (_tmp_3 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_3);
    }
    return RxRoom.createObservable(__db, false, new String[]{"OverdueAppointment"}, new Callable<List<OverdueAppointment>>() {
      @Override
      public List<OverdueAppointment> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfDateOfBirth = CursorUtil.getColumnIndexOrThrow(_cursor, "dateOfBirth");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "age_value");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "age_updatedAt");
          final int _cursorIndexOfPatientAssignedFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "patientAssignedFacilityUuid");
          final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_uuid");
          final int _cursorIndexOfPatientUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_patientUuid");
          final int _cursorIndexOfFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_facilityUuid");
          final int _cursorIndexOfScheduledDate = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_scheduledDate");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_status");
          final int _cursorIndexOfCancelReason = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_cancelReason");
          final int _cursorIndexOfRemindOn = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_remindOn");
          final int _cursorIndexOfAgreedToVisit = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_agreedToVisit");
          final int _cursorIndexOfAppointmentType = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_appointmentType");
          final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_syncStatus");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_createdAt");
          final int _cursorIndexOfUpdatedAt_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_updatedAt");
          final int _cursorIndexOfCreationFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_creationFacilityUuid");
          final int _cursorIndexOfUuid_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_uuid");
          final int _cursorIndexOfPatientUuid_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_patientUuid");
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_number");
          final int _cursorIndexOfPhoneType = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_phoneType");
          final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_active");
          final int _cursorIndexOfCreatedAt_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_createdAt");
          final int _cursorIndexOfUpdatedAt_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_updatedAt");
          final int _cursorIndexOfDiagnosedWithDiabetes = CursorUtil.getColumnIndexOrThrow(_cursor, "diagnosedWithDiabetes");
          final int _cursorIndexOfDiagnosedWithHypertension = CursorUtil.getColumnIndexOrThrow(_cursor, "diagnosedWithHypertension");
          final int _cursorIndexOfPatientLastSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "patientLastSeen");
          final int _cursorIndexOfIsAtHighRisk = CursorUtil.getColumnIndexOrThrow(_cursor, "isAtHighRisk");
          final int _cursorIndexOfStreetAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_address_streetAddress");
          final int _cursorIndexOfColonyOrVillage = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_address_colonyOrVillage");
          final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_address_district");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_address_state");
          final int _cursorIndexOfAppointmentFacilityName = CursorUtil.getColumnIndexOrThrow(_cursor, "appointmentFacilityName");
          final List<OverdueAppointment> _result = new ArrayList<OverdueAppointment>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final OverdueAppointment _item;
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            final Gender _tmpGender;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfGender);
            _tmpGender = __roomTypeConverter.toEnum(_tmp_4);
            final LocalDate _tmpDateOfBirth;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfDateOfBirth);
            _tmpDateOfBirth = __localDateRoomTypeConverter.toLocalDate(_tmp_5);
            final UUID _tmpPatientAssignedFacilityUuid;
            final String _tmp_6;
            _tmp_6 = _cursor.getString(_cursorIndexOfPatientAssignedFacilityUuid);
            _tmpPatientAssignedFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_6);
            final Answer _tmpDiagnosedWithDiabetes;
            final String _tmp_7;
            _tmp_7 = _cursor.getString(_cursorIndexOfDiagnosedWithDiabetes);
            _tmpDiagnosedWithDiabetes = __roomTypeConverter_1.toEnum(_tmp_7);
            final Answer _tmpDiagnosedWithHypertension;
            final String _tmp_8;
            _tmp_8 = _cursor.getString(_cursorIndexOfDiagnosedWithHypertension);
            _tmpDiagnosedWithHypertension = __roomTypeConverter_1.toEnum(_tmp_8);
            final Instant _tmpPatientLastSeen;
            final String _tmp_9;
            _tmp_9 = _cursor.getString(_cursorIndexOfPatientLastSeen);
            _tmpPatientLastSeen = __instantRoomTypeConverter.toInstant(_tmp_9);
            final boolean _tmpIsAtHighRisk;
            final int _tmp_10;
            _tmp_10 = _cursor.getInt(_cursorIndexOfIsAtHighRisk);
            _tmpIsAtHighRisk = _tmp_10 != 0;
            final String _tmpAppointmentFacilityName;
            _tmpAppointmentFacilityName = _cursor.getString(_cursorIndexOfAppointmentFacilityName);
            final Age _tmpAge;
            if (! (_cursor.isNull(_cursorIndexOfValue) && _cursor.isNull(_cursorIndexOfUpdatedAt))) {
              final int _tmpValue;
              _tmpValue = _cursor.getInt(_cursorIndexOfValue);
              final Instant _tmpUpdatedAt;
              final String _tmp_11;
              _tmp_11 = _cursor.getString(_cursorIndexOfUpdatedAt);
              _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_11);
              _tmpAge = new Age(_tmpValue,_tmpUpdatedAt);
            }  else  {
              _tmpAge = null;
            }
            final Appointment _tmpAppointment;
            if (! (_cursor.isNull(_cursorIndexOfUuid) && _cursor.isNull(_cursorIndexOfPatientUuid) && _cursor.isNull(_cursorIndexOfFacilityUuid) && _cursor.isNull(_cursorIndexOfScheduledDate) && _cursor.isNull(_cursorIndexOfStatus) && _cursor.isNull(_cursorIndexOfCancelReason) && _cursor.isNull(_cursorIndexOfRemindOn) && _cursor.isNull(_cursorIndexOfAgreedToVisit) && _cursor.isNull(_cursorIndexOfAppointmentType) && _cursor.isNull(_cursorIndexOfSyncStatus) && _cursor.isNull(_cursorIndexOfCreatedAt) && _cursor.isNull(_cursorIndexOfUpdatedAt_1) && _cursor.isNull(_cursorIndexOfCreationFacilityUuid))) {
              final UUID _tmpUuid;
              final String _tmp_12;
              _tmp_12 = _cursor.getString(_cursorIndexOfUuid);
              _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp_12);
              final UUID _tmpPatientUuid;
              final String _tmp_13;
              _tmp_13 = _cursor.getString(_cursorIndexOfPatientUuid);
              _tmpPatientUuid = __uuidRoomTypeConverter.toUuid(_tmp_13);
              final UUID _tmpFacilityUuid;
              final String _tmp_14;
              _tmp_14 = _cursor.getString(_cursorIndexOfFacilityUuid);
              _tmpFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_14);
              final LocalDate _tmpScheduledDate;
              final String _tmp_15;
              _tmp_15 = _cursor.getString(_cursorIndexOfScheduledDate);
              _tmpScheduledDate = __localDateRoomTypeConverter.toLocalDate(_tmp_15);
              final Appointment.Status _tmpStatus;
              final String _tmp_16;
              _tmp_16 = _cursor.getString(_cursorIndexOfStatus);
              _tmpStatus = __roomTypeConverter_2.toEnum(_tmp_16);
              final AppointmentCancelReason _tmpCancelReason;
              final String _tmp_17;
              _tmp_17 = _cursor.getString(_cursorIndexOfCancelReason);
              _tmpCancelReason = __roomTypeConverter_3.toEnum(_tmp_17);
              final LocalDate _tmpRemindOn;
              final String _tmp_18;
              _tmp_18 = _cursor.getString(_cursorIndexOfRemindOn);
              _tmpRemindOn = __localDateRoomTypeConverter.toLocalDate(_tmp_18);
              final Boolean _tmpAgreedToVisit;
              final Integer _tmp_19;
              if (_cursor.isNull(_cursorIndexOfAgreedToVisit)) {
                _tmp_19 = null;
              } else {
                _tmp_19 = _cursor.getInt(_cursorIndexOfAgreedToVisit);
              }
              _tmpAgreedToVisit = _tmp_19 == null ? null : _tmp_19 != 0;
              final Appointment.AppointmentType _tmpAppointmentType;
              final String _tmp_20;
              _tmp_20 = _cursor.getString(_cursorIndexOfAppointmentType);
              _tmpAppointmentType = __roomTypeConverter_4.toEnum(_tmp_20);
              final SyncStatus _tmpSyncStatus;
              final String _tmp_21;
              _tmp_21 = _cursor.getString(_cursorIndexOfSyncStatus);
              _tmpSyncStatus = __roomTypeConverter_5.toEnum(_tmp_21);
              final Instant _tmpCreatedAt;
              final String _tmp_22;
              _tmp_22 = _cursor.getString(_cursorIndexOfCreatedAt);
              _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_22);
              final Instant _tmpUpdatedAt_1;
              final String _tmp_23;
              _tmp_23 = _cursor.getString(_cursorIndexOfUpdatedAt_1);
              _tmpUpdatedAt_1 = __instantRoomTypeConverter.toInstant(_tmp_23);
              final UUID _tmpCreationFacilityUuid;
              final String _tmp_24;
              _tmp_24 = _cursor.getString(_cursorIndexOfCreationFacilityUuid);
              _tmpCreationFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_24);
              _tmpAppointment = new Appointment(_tmpUuid,_tmpPatientUuid,_tmpFacilityUuid,_tmpScheduledDate,_tmpStatus,_tmpCancelReason,_tmpRemindOn,_tmpAgreedToVisit,_tmpAppointmentType,_tmpSyncStatus,_tmpCreatedAt,_tmpUpdatedAt_1,null,_tmpCreationFacilityUuid);
            }  else  {
              _tmpAppointment = null;
            }
            final PatientPhoneNumber _tmpPhoneNumber;
            if (! (_cursor.isNull(_cursorIndexOfUuid_1) && _cursor.isNull(_cursorIndexOfPatientUuid_1) && _cursor.isNull(_cursorIndexOfNumber) && _cursor.isNull(_cursorIndexOfPhoneType) && _cursor.isNull(_cursorIndexOfActive) && _cursor.isNull(_cursorIndexOfCreatedAt_1) && _cursor.isNull(_cursorIndexOfUpdatedAt_2))) {
              final UUID _tmpUuid_1;
              final String _tmp_25;
              _tmp_25 = _cursor.getString(_cursorIndexOfUuid_1);
              _tmpUuid_1 = __uuidRoomTypeConverter.toUuid(_tmp_25);
              final UUID _tmpPatientUuid_1;
              final String _tmp_26;
              _tmp_26 = _cursor.getString(_cursorIndexOfPatientUuid_1);
              _tmpPatientUuid_1 = __uuidRoomTypeConverter.toUuid(_tmp_26);
              final String _tmpNumber;
              _tmpNumber = _cursor.getString(_cursorIndexOfNumber);
              final PatientPhoneNumberType _tmpPhoneType;
              final String _tmp_27;
              _tmp_27 = _cursor.getString(_cursorIndexOfPhoneType);
              _tmpPhoneType = __roomTypeConverter_6.toEnum(_tmp_27);
              final boolean _tmpActive;
              final int _tmp_28;
              _tmp_28 = _cursor.getInt(_cursorIndexOfActive);
              _tmpActive = _tmp_28 != 0;
              final Instant _tmpCreatedAt_1;
              final String _tmp_29;
              _tmp_29 = _cursor.getString(_cursorIndexOfCreatedAt_1);
              _tmpCreatedAt_1 = __instantRoomTypeConverter.toInstant(_tmp_29);
              final Instant _tmpUpdatedAt_2;
              final String _tmp_30;
              _tmp_30 = _cursor.getString(_cursorIndexOfUpdatedAt_2);
              _tmpUpdatedAt_2 = __instantRoomTypeConverter.toInstant(_tmp_30);
              _tmpPhoneNumber = new PatientPhoneNumber(_tmpUuid_1,_tmpPatientUuid_1,_tmpNumber,_tmpPhoneType,_tmpActive,_tmpCreatedAt_1,_tmpUpdatedAt_2,null);
            }  else  {
              _tmpPhoneNumber = null;
            }
            final OverduePatientAddress _tmpPatientAddress;
            if (! (_cursor.isNull(_cursorIndexOfStreetAddress) && _cursor.isNull(_cursorIndexOfColonyOrVillage) && _cursor.isNull(_cursorIndexOfDistrict) && _cursor.isNull(_cursorIndexOfState))) {
              final String _tmpStreetAddress;
              _tmpStreetAddress = _cursor.getString(_cursorIndexOfStreetAddress);
              final String _tmpColonyOrVillage;
              _tmpColonyOrVillage = _cursor.getString(_cursorIndexOfColonyOrVillage);
              final String _tmpDistrict;
              _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
              final String _tmpState;
              _tmpState = _cursor.getString(_cursorIndexOfState);
              _tmpPatientAddress = new OverduePatientAddress(_tmpStreetAddress,_tmpColonyOrVillage,_tmpDistrict,_tmpState);
            }  else  {
              _tmpPatientAddress = null;
            }
            _item = new OverdueAppointment(_tmpFullName,_tmpGender,_tmpDateOfBirth,_tmpAge,_tmpAppointment,_tmpPhoneNumber,_tmpPatientAddress,_tmpIsAtHighRisk,_tmpPatientLastSeen,_tmpDiagnosedWithDiabetes,_tmpDiagnosedWithHypertension,_tmpPatientAssignedFacilityUuid,_tmpAppointmentFacilityName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public DataSource.Factory<Integer, OverdueAppointment> overdueAtFacilityDataSource(
      final UUID facilityUuid, final LocalDate scheduledBefore, final LocalDate scheduledAfter) {
    final String _sql = "\n"
            + "      SELECT * FROM OverdueAppointment\n"
            + "      WHERE \n"
            + "        IFNULL(patientAssignedFacilityUuid, appt_facilityUuid) = ? \n"
            + "        AND (appt_scheduledDate < ? AND appt_scheduledDate > ?)\n"
            + "        AND (appt_remindOn < ? OR appt_remindOn IS NULL)\n"
            + "        GROUP BY appt_patientUuid\n"
            + "        ORDER BY isAtHighRisk DESC, appt_scheduledDate DESC, appt_updatedAt ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __uuidRoomTypeConverter.fromUuid(facilityUuid);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    _argIndex = 2;
    final String _tmp_1;
    _tmp_1 = __localDateRoomTypeConverter.fromLocalDate(scheduledBefore);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_1);
    }
    _argIndex = 3;
    final String _tmp_2;
    _tmp_2 = __localDateRoomTypeConverter.fromLocalDate(scheduledAfter);
    if (_tmp_2 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_2);
    }
    _argIndex = 4;
    final String _tmp_3;
    _tmp_3 = __localDateRoomTypeConverter.fromLocalDate(scheduledBefore);
    if (_tmp_3 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_3);
    }
    return new DataSource.Factory<Integer, OverdueAppointment>() {
      @Override
      public LimitOffsetDataSource<OverdueAppointment> create() {
        return new LimitOffsetDataSource<OverdueAppointment>(__db, _statement, false , "OverdueAppointment") {
          @Override
          protected List<OverdueAppointment> convertRows(Cursor cursor) {
            final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(cursor, "fullName");
            final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(cursor, "gender");
            final int _cursorIndexOfDateOfBirth = CursorUtil.getColumnIndexOrThrow(cursor, "dateOfBirth");
            final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(cursor, "age_value");
            final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(cursor, "age_updatedAt");
            final int _cursorIndexOfPatientAssignedFacilityUuid = CursorUtil.getColumnIndexOrThrow(cursor, "patientAssignedFacilityUuid");
            final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(cursor, "appt_uuid");
            final int _cursorIndexOfPatientUuid = CursorUtil.getColumnIndexOrThrow(cursor, "appt_patientUuid");
            final int _cursorIndexOfFacilityUuid = CursorUtil.getColumnIndexOrThrow(cursor, "appt_facilityUuid");
            final int _cursorIndexOfScheduledDate = CursorUtil.getColumnIndexOrThrow(cursor, "appt_scheduledDate");
            final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(cursor, "appt_status");
            final int _cursorIndexOfCancelReason = CursorUtil.getColumnIndexOrThrow(cursor, "appt_cancelReason");
            final int _cursorIndexOfRemindOn = CursorUtil.getColumnIndexOrThrow(cursor, "appt_remindOn");
            final int _cursorIndexOfAgreedToVisit = CursorUtil.getColumnIndexOrThrow(cursor, "appt_agreedToVisit");
            final int _cursorIndexOfAppointmentType = CursorUtil.getColumnIndexOrThrow(cursor, "appt_appointmentType");
            final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(cursor, "appt_syncStatus");
            final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(cursor, "appt_createdAt");
            final int _cursorIndexOfUpdatedAt_1 = CursorUtil.getColumnIndexOrThrow(cursor, "appt_updatedAt");
            final int _cursorIndexOfCreationFacilityUuid = CursorUtil.getColumnIndexOrThrow(cursor, "appt_creationFacilityUuid");
            final int _cursorIndexOfUuid_1 = CursorUtil.getColumnIndexOrThrow(cursor, "phone_uuid");
            final int _cursorIndexOfPatientUuid_1 = CursorUtil.getColumnIndexOrThrow(cursor, "phone_patientUuid");
            final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(cursor, "phone_number");
            final int _cursorIndexOfPhoneType = CursorUtil.getColumnIndexOrThrow(cursor, "phone_phoneType");
            final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(cursor, "phone_active");
            final int _cursorIndexOfCreatedAt_1 = CursorUtil.getColumnIndexOrThrow(cursor, "phone_createdAt");
            final int _cursorIndexOfUpdatedAt_2 = CursorUtil.getColumnIndexOrThrow(cursor, "phone_updatedAt");
            final int _cursorIndexOfDiagnosedWithDiabetes = CursorUtil.getColumnIndexOrThrow(cursor, "diagnosedWithDiabetes");
            final int _cursorIndexOfDiagnosedWithHypertension = CursorUtil.getColumnIndexOrThrow(cursor, "diagnosedWithHypertension");
            final int _cursorIndexOfPatientLastSeen = CursorUtil.getColumnIndexOrThrow(cursor, "patientLastSeen");
            final int _cursorIndexOfIsAtHighRisk = CursorUtil.getColumnIndexOrThrow(cursor, "isAtHighRisk");
            final int _cursorIndexOfStreetAddress = CursorUtil.getColumnIndexOrThrow(cursor, "patient_address_streetAddress");
            final int _cursorIndexOfColonyOrVillage = CursorUtil.getColumnIndexOrThrow(cursor, "patient_address_colonyOrVillage");
            final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(cursor, "patient_address_district");
            final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(cursor, "patient_address_state");
            final int _cursorIndexOfAppointmentFacilityName = CursorUtil.getColumnIndexOrThrow(cursor, "appointmentFacilityName");
            final List<OverdueAppointment> _res = new ArrayList<OverdueAppointment>(cursor.getCount());
            while(cursor.moveToNext()) {
              final OverdueAppointment _item;
              final String _tmpFullName;
              _tmpFullName = cursor.getString(_cursorIndexOfFullName);
              final Gender _tmpGender;
              final String _tmp_4;
              _tmp_4 = cursor.getString(_cursorIndexOfGender);
              _tmpGender = __roomTypeConverter.toEnum(_tmp_4);
              final LocalDate _tmpDateOfBirth;
              final String _tmp_5;
              _tmp_5 = cursor.getString(_cursorIndexOfDateOfBirth);
              _tmpDateOfBirth = __localDateRoomTypeConverter.toLocalDate(_tmp_5);
              final UUID _tmpPatientAssignedFacilityUuid;
              final String _tmp_6;
              _tmp_6 = cursor.getString(_cursorIndexOfPatientAssignedFacilityUuid);
              _tmpPatientAssignedFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_6);
              final Answer _tmpDiagnosedWithDiabetes;
              final String _tmp_7;
              _tmp_7 = cursor.getString(_cursorIndexOfDiagnosedWithDiabetes);
              _tmpDiagnosedWithDiabetes = __roomTypeConverter_1.toEnum(_tmp_7);
              final Answer _tmpDiagnosedWithHypertension;
              final String _tmp_8;
              _tmp_8 = cursor.getString(_cursorIndexOfDiagnosedWithHypertension);
              _tmpDiagnosedWithHypertension = __roomTypeConverter_1.toEnum(_tmp_8);
              final Instant _tmpPatientLastSeen;
              final String _tmp_9;
              _tmp_9 = cursor.getString(_cursorIndexOfPatientLastSeen);
              _tmpPatientLastSeen = __instantRoomTypeConverter.toInstant(_tmp_9);
              final boolean _tmpIsAtHighRisk;
              final int _tmp_10;
              _tmp_10 = cursor.getInt(_cursorIndexOfIsAtHighRisk);
              _tmpIsAtHighRisk = _tmp_10 != 0;
              final String _tmpAppointmentFacilityName;
              _tmpAppointmentFacilityName = cursor.getString(_cursorIndexOfAppointmentFacilityName);
              final Age _tmpAge;
              if (! (cursor.isNull(_cursorIndexOfValue) && cursor.isNull(_cursorIndexOfUpdatedAt))) {
                final int _tmpValue;
                _tmpValue = cursor.getInt(_cursorIndexOfValue);
                final Instant _tmpUpdatedAt;
                final String _tmp_11;
                _tmp_11 = cursor.getString(_cursorIndexOfUpdatedAt);
                _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_11);
                _tmpAge = new Age(_tmpValue,_tmpUpdatedAt);
              }  else  {
                _tmpAge = null;
              }
              final Appointment _tmpAppointment;
              if (! (cursor.isNull(_cursorIndexOfUuid) && cursor.isNull(_cursorIndexOfPatientUuid) && cursor.isNull(_cursorIndexOfFacilityUuid) && cursor.isNull(_cursorIndexOfScheduledDate) && cursor.isNull(_cursorIndexOfStatus) && cursor.isNull(_cursorIndexOfCancelReason) && cursor.isNull(_cursorIndexOfRemindOn) && cursor.isNull(_cursorIndexOfAgreedToVisit) && cursor.isNull(_cursorIndexOfAppointmentType) && cursor.isNull(_cursorIndexOfSyncStatus) && cursor.isNull(_cursorIndexOfCreatedAt) && cursor.isNull(_cursorIndexOfUpdatedAt_1) && cursor.isNull(_cursorIndexOfCreationFacilityUuid))) {
                final UUID _tmpUuid;
                final String _tmp_12;
                _tmp_12 = cursor.getString(_cursorIndexOfUuid);
                _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp_12);
                final UUID _tmpPatientUuid;
                final String _tmp_13;
                _tmp_13 = cursor.getString(_cursorIndexOfPatientUuid);
                _tmpPatientUuid = __uuidRoomTypeConverter.toUuid(_tmp_13);
                final UUID _tmpFacilityUuid;
                final String _tmp_14;
                _tmp_14 = cursor.getString(_cursorIndexOfFacilityUuid);
                _tmpFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_14);
                final LocalDate _tmpScheduledDate;
                final String _tmp_15;
                _tmp_15 = cursor.getString(_cursorIndexOfScheduledDate);
                _tmpScheduledDate = __localDateRoomTypeConverter.toLocalDate(_tmp_15);
                final Appointment.Status _tmpStatus;
                final String _tmp_16;
                _tmp_16 = cursor.getString(_cursorIndexOfStatus);
                _tmpStatus = __roomTypeConverter_2.toEnum(_tmp_16);
                final AppointmentCancelReason _tmpCancelReason;
                final String _tmp_17;
                _tmp_17 = cursor.getString(_cursorIndexOfCancelReason);
                _tmpCancelReason = __roomTypeConverter_3.toEnum(_tmp_17);
                final LocalDate _tmpRemindOn;
                final String _tmp_18;
                _tmp_18 = cursor.getString(_cursorIndexOfRemindOn);
                _tmpRemindOn = __localDateRoomTypeConverter.toLocalDate(_tmp_18);
                final Boolean _tmpAgreedToVisit;
                final Integer _tmp_19;
                if (cursor.isNull(_cursorIndexOfAgreedToVisit)) {
                  _tmp_19 = null;
                } else {
                  _tmp_19 = cursor.getInt(_cursorIndexOfAgreedToVisit);
                }
                _tmpAgreedToVisit = _tmp_19 == null ? null : _tmp_19 != 0;
                final Appointment.AppointmentType _tmpAppointmentType;
                final String _tmp_20;
                _tmp_20 = cursor.getString(_cursorIndexOfAppointmentType);
                _tmpAppointmentType = __roomTypeConverter_4.toEnum(_tmp_20);
                final SyncStatus _tmpSyncStatus;
                final String _tmp_21;
                _tmp_21 = cursor.getString(_cursorIndexOfSyncStatus);
                _tmpSyncStatus = __roomTypeConverter_5.toEnum(_tmp_21);
                final Instant _tmpCreatedAt;
                final String _tmp_22;
                _tmp_22 = cursor.getString(_cursorIndexOfCreatedAt);
                _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_22);
                final Instant _tmpUpdatedAt_1;
                final String _tmp_23;
                _tmp_23 = cursor.getString(_cursorIndexOfUpdatedAt_1);
                _tmpUpdatedAt_1 = __instantRoomTypeConverter.toInstant(_tmp_23);
                final UUID _tmpCreationFacilityUuid;
                final String _tmp_24;
                _tmp_24 = cursor.getString(_cursorIndexOfCreationFacilityUuid);
                _tmpCreationFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_24);
                _tmpAppointment = new Appointment(_tmpUuid,_tmpPatientUuid,_tmpFacilityUuid,_tmpScheduledDate,_tmpStatus,_tmpCancelReason,_tmpRemindOn,_tmpAgreedToVisit,_tmpAppointmentType,_tmpSyncStatus,_tmpCreatedAt,_tmpUpdatedAt_1,null,_tmpCreationFacilityUuid);
              }  else  {
                _tmpAppointment = null;
              }
              final PatientPhoneNumber _tmpPhoneNumber;
              if (! (cursor.isNull(_cursorIndexOfUuid_1) && cursor.isNull(_cursorIndexOfPatientUuid_1) && cursor.isNull(_cursorIndexOfNumber) && cursor.isNull(_cursorIndexOfPhoneType) && cursor.isNull(_cursorIndexOfActive) && cursor.isNull(_cursorIndexOfCreatedAt_1) && cursor.isNull(_cursorIndexOfUpdatedAt_2))) {
                final UUID _tmpUuid_1;
                final String _tmp_25;
                _tmp_25 = cursor.getString(_cursorIndexOfUuid_1);
                _tmpUuid_1 = __uuidRoomTypeConverter.toUuid(_tmp_25);
                final UUID _tmpPatientUuid_1;
                final String _tmp_26;
                _tmp_26 = cursor.getString(_cursorIndexOfPatientUuid_1);
                _tmpPatientUuid_1 = __uuidRoomTypeConverter.toUuid(_tmp_26);
                final String _tmpNumber;
                _tmpNumber = cursor.getString(_cursorIndexOfNumber);
                final PatientPhoneNumberType _tmpPhoneType;
                final String _tmp_27;
                _tmp_27 = cursor.getString(_cursorIndexOfPhoneType);
                _tmpPhoneType = __roomTypeConverter_6.toEnum(_tmp_27);
                final boolean _tmpActive;
                final int _tmp_28;
                _tmp_28 = cursor.getInt(_cursorIndexOfActive);
                _tmpActive = _tmp_28 != 0;
                final Instant _tmpCreatedAt_1;
                final String _tmp_29;
                _tmp_29 = cursor.getString(_cursorIndexOfCreatedAt_1);
                _tmpCreatedAt_1 = __instantRoomTypeConverter.toInstant(_tmp_29);
                final Instant _tmpUpdatedAt_2;
                final String _tmp_30;
                _tmp_30 = cursor.getString(_cursorIndexOfUpdatedAt_2);
                _tmpUpdatedAt_2 = __instantRoomTypeConverter.toInstant(_tmp_30);
                _tmpPhoneNumber = new PatientPhoneNumber(_tmpUuid_1,_tmpPatientUuid_1,_tmpNumber,_tmpPhoneType,_tmpActive,_tmpCreatedAt_1,_tmpUpdatedAt_2,null);
              }  else  {
                _tmpPhoneNumber = null;
              }
              final OverduePatientAddress _tmpPatientAddress;
              if (! (cursor.isNull(_cursorIndexOfStreetAddress) && cursor.isNull(_cursorIndexOfColonyOrVillage) && cursor.isNull(_cursorIndexOfDistrict) && cursor.isNull(_cursorIndexOfState))) {
                final String _tmpStreetAddress;
                _tmpStreetAddress = cursor.getString(_cursorIndexOfStreetAddress);
                final String _tmpColonyOrVillage;
                _tmpColonyOrVillage = cursor.getString(_cursorIndexOfColonyOrVillage);
                final String _tmpDistrict;
                _tmpDistrict = cursor.getString(_cursorIndexOfDistrict);
                final String _tmpState;
                _tmpState = cursor.getString(_cursorIndexOfState);
                _tmpPatientAddress = new OverduePatientAddress(_tmpStreetAddress,_tmpColonyOrVillage,_tmpDistrict,_tmpState);
              }  else  {
                _tmpPatientAddress = null;
              }
              _item = new OverdueAppointment(_tmpFullName,_tmpGender,_tmpDateOfBirth,_tmpAge,_tmpAppointment,_tmpPhoneNumber,_tmpPatientAddress,_tmpIsAtHighRisk,_tmpPatientLastSeen,_tmpDiagnosedWithDiabetes,_tmpDiagnosedWithHypertension,_tmpPatientAssignedFacilityUuid,_tmpAppointmentFacilityName);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  @Override
  public Observable<List<Integer>> overdueAtFacilityCount(final UUID facilityUuid,
      final LocalDate scheduledBefore, final LocalDate scheduledAfter) {
    final String _sql = "\n"
            + "      SELECT COUNT(appt_uuid) FROM OverdueAppointment\n"
            + "      WHERE \n"
            + "        IFNULL(patientAssignedFacilityUuid, appt_facilityUuid) = ? \n"
            + "        AND (appt_scheduledDate < ? AND appt_scheduledDate > ?)\n"
            + "        AND (appt_remindOn < ? OR appt_remindOn IS NULL)\n"
            + "        GROUP BY appt_patientUuid\n"
            + "        ORDER BY isAtHighRisk DESC, appt_scheduledDate DESC, appt_updatedAt ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __uuidRoomTypeConverter.fromUuid(facilityUuid);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    _argIndex = 2;
    final String _tmp_1;
    _tmp_1 = __localDateRoomTypeConverter.fromLocalDate(scheduledBefore);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_1);
    }
    _argIndex = 3;
    final String _tmp_2;
    _tmp_2 = __localDateRoomTypeConverter.fromLocalDate(scheduledAfter);
    if (_tmp_2 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_2);
    }
    _argIndex = 4;
    final String _tmp_3;
    _tmp_3 = __localDateRoomTypeConverter.fromLocalDate(scheduledBefore);
    if (_tmp_3 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_3);
    }
    return RxRoom.createObservable(__db, false, new String[]{"OverdueAppointment"}, new Callable<List<Integer>>() {
      @Override
      public List<Integer> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<Integer> _result = new ArrayList<Integer>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Integer _item;
            final Integer _tmp_4;
            if (_cursor.isNull(0)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getInt(0);
            }
            _item = _tmp_4;
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public OverdueAppointment latestForPatient(final UUID patientUUID,
      final LocalDate scheduledDate) {
    final String _sql = "\n"
            + "      SELECT * FROM OverdueAppointment\n"
            + "      WHERE \n"
            + "        appt_patientUuid = ?\n"
            + "        AND appt_scheduledDate < ?\n"
            + "        AND (appt_remindOn < ? OR appt_remindOn IS NULL)\n"
            + "      GROUP BY appt_patientUuid HAVING MAX(appt_scheduledDate)\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __uuidRoomTypeConverter.fromUuid(patientUUID);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    _argIndex = 2;
    final String _tmp_1;
    _tmp_1 = __localDateRoomTypeConverter.fromLocalDate(scheduledDate);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_1);
    }
    _argIndex = 3;
    final String _tmp_2;
    _tmp_2 = __localDateRoomTypeConverter.fromLocalDate(scheduledDate);
    if (_tmp_2 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_2);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
      final int _cursorIndexOfDateOfBirth = CursorUtil.getColumnIndexOrThrow(_cursor, "dateOfBirth");
      final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "age_value");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "age_updatedAt");
      final int _cursorIndexOfPatientAssignedFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "patientAssignedFacilityUuid");
      final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_uuid");
      final int _cursorIndexOfPatientUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_patientUuid");
      final int _cursorIndexOfFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_facilityUuid");
      final int _cursorIndexOfScheduledDate = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_scheduledDate");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_status");
      final int _cursorIndexOfCancelReason = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_cancelReason");
      final int _cursorIndexOfRemindOn = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_remindOn");
      final int _cursorIndexOfAgreedToVisit = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_agreedToVisit");
      final int _cursorIndexOfAppointmentType = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_appointmentType");
      final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_syncStatus");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_createdAt");
      final int _cursorIndexOfUpdatedAt_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_updatedAt");
      final int _cursorIndexOfCreationFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "appt_creationFacilityUuid");
      final int _cursorIndexOfUuid_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_uuid");
      final int _cursorIndexOfPatientUuid_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_patientUuid");
      final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_number");
      final int _cursorIndexOfPhoneType = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_phoneType");
      final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_active");
      final int _cursorIndexOfCreatedAt_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_createdAt");
      final int _cursorIndexOfUpdatedAt_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "phone_updatedAt");
      final int _cursorIndexOfDiagnosedWithDiabetes = CursorUtil.getColumnIndexOrThrow(_cursor, "diagnosedWithDiabetes");
      final int _cursorIndexOfDiagnosedWithHypertension = CursorUtil.getColumnIndexOrThrow(_cursor, "diagnosedWithHypertension");
      final int _cursorIndexOfPatientLastSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "patientLastSeen");
      final int _cursorIndexOfIsAtHighRisk = CursorUtil.getColumnIndexOrThrow(_cursor, "isAtHighRisk");
      final int _cursorIndexOfStreetAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_address_streetAddress");
      final int _cursorIndexOfColonyOrVillage = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_address_colonyOrVillage");
      final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_address_district");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_address_state");
      final int _cursorIndexOfAppointmentFacilityName = CursorUtil.getColumnIndexOrThrow(_cursor, "appointmentFacilityName");
      final OverdueAppointment _result;
      if(_cursor.moveToFirst()) {
        final String _tmpFullName;
        _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
        final Gender _tmpGender;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfGender);
        _tmpGender = __roomTypeConverter.toEnum(_tmp_3);
        final LocalDate _tmpDateOfBirth;
        final String _tmp_4;
        _tmp_4 = _cursor.getString(_cursorIndexOfDateOfBirth);
        _tmpDateOfBirth = __localDateRoomTypeConverter.toLocalDate(_tmp_4);
        final UUID _tmpPatientAssignedFacilityUuid;
        final String _tmp_5;
        _tmp_5 = _cursor.getString(_cursorIndexOfPatientAssignedFacilityUuid);
        _tmpPatientAssignedFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_5);
        final Answer _tmpDiagnosedWithDiabetes;
        final String _tmp_6;
        _tmp_6 = _cursor.getString(_cursorIndexOfDiagnosedWithDiabetes);
        _tmpDiagnosedWithDiabetes = __roomTypeConverter_1.toEnum(_tmp_6);
        final Answer _tmpDiagnosedWithHypertension;
        final String _tmp_7;
        _tmp_7 = _cursor.getString(_cursorIndexOfDiagnosedWithHypertension);
        _tmpDiagnosedWithHypertension = __roomTypeConverter_1.toEnum(_tmp_7);
        final Instant _tmpPatientLastSeen;
        final String _tmp_8;
        _tmp_8 = _cursor.getString(_cursorIndexOfPatientLastSeen);
        _tmpPatientLastSeen = __instantRoomTypeConverter.toInstant(_tmp_8);
        final boolean _tmpIsAtHighRisk;
        final int _tmp_9;
        _tmp_9 = _cursor.getInt(_cursorIndexOfIsAtHighRisk);
        _tmpIsAtHighRisk = _tmp_9 != 0;
        final String _tmpAppointmentFacilityName;
        _tmpAppointmentFacilityName = _cursor.getString(_cursorIndexOfAppointmentFacilityName);
        final Age _tmpAge;
        if (! (_cursor.isNull(_cursorIndexOfValue) && _cursor.isNull(_cursorIndexOfUpdatedAt))) {
          final int _tmpValue;
          _tmpValue = _cursor.getInt(_cursorIndexOfValue);
          final Instant _tmpUpdatedAt;
          final String _tmp_10;
          _tmp_10 = _cursor.getString(_cursorIndexOfUpdatedAt);
          _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_10);
          _tmpAge = new Age(_tmpValue,_tmpUpdatedAt);
        }  else  {
          _tmpAge = null;
        }
        final Appointment _tmpAppointment;
        if (! (_cursor.isNull(_cursorIndexOfUuid) && _cursor.isNull(_cursorIndexOfPatientUuid) && _cursor.isNull(_cursorIndexOfFacilityUuid) && _cursor.isNull(_cursorIndexOfScheduledDate) && _cursor.isNull(_cursorIndexOfStatus) && _cursor.isNull(_cursorIndexOfCancelReason) && _cursor.isNull(_cursorIndexOfRemindOn) && _cursor.isNull(_cursorIndexOfAgreedToVisit) && _cursor.isNull(_cursorIndexOfAppointmentType) && _cursor.isNull(_cursorIndexOfSyncStatus) && _cursor.isNull(_cursorIndexOfCreatedAt) && _cursor.isNull(_cursorIndexOfUpdatedAt_1) && _cursor.isNull(_cursorIndexOfCreationFacilityUuid))) {
          final UUID _tmpUuid;
          final String _tmp_11;
          _tmp_11 = _cursor.getString(_cursorIndexOfUuid);
          _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp_11);
          final UUID _tmpPatientUuid;
          final String _tmp_12;
          _tmp_12 = _cursor.getString(_cursorIndexOfPatientUuid);
          _tmpPatientUuid = __uuidRoomTypeConverter.toUuid(_tmp_12);
          final UUID _tmpFacilityUuid;
          final String _tmp_13;
          _tmp_13 = _cursor.getString(_cursorIndexOfFacilityUuid);
          _tmpFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_13);
          final LocalDate _tmpScheduledDate;
          final String _tmp_14;
          _tmp_14 = _cursor.getString(_cursorIndexOfScheduledDate);
          _tmpScheduledDate = __localDateRoomTypeConverter.toLocalDate(_tmp_14);
          final Appointment.Status _tmpStatus;
          final String _tmp_15;
          _tmp_15 = _cursor.getString(_cursorIndexOfStatus);
          _tmpStatus = __roomTypeConverter_2.toEnum(_tmp_15);
          final AppointmentCancelReason _tmpCancelReason;
          final String _tmp_16;
          _tmp_16 = _cursor.getString(_cursorIndexOfCancelReason);
          _tmpCancelReason = __roomTypeConverter_3.toEnum(_tmp_16);
          final LocalDate _tmpRemindOn;
          final String _tmp_17;
          _tmp_17 = _cursor.getString(_cursorIndexOfRemindOn);
          _tmpRemindOn = __localDateRoomTypeConverter.toLocalDate(_tmp_17);
          final Boolean _tmpAgreedToVisit;
          final Integer _tmp_18;
          if (_cursor.isNull(_cursorIndexOfAgreedToVisit)) {
            _tmp_18 = null;
          } else {
            _tmp_18 = _cursor.getInt(_cursorIndexOfAgreedToVisit);
          }
          _tmpAgreedToVisit = _tmp_18 == null ? null : _tmp_18 != 0;
          final Appointment.AppointmentType _tmpAppointmentType;
          final String _tmp_19;
          _tmp_19 = _cursor.getString(_cursorIndexOfAppointmentType);
          _tmpAppointmentType = __roomTypeConverter_4.toEnum(_tmp_19);
          final SyncStatus _tmpSyncStatus;
          final String _tmp_20;
          _tmp_20 = _cursor.getString(_cursorIndexOfSyncStatus);
          _tmpSyncStatus = __roomTypeConverter_5.toEnum(_tmp_20);
          final Instant _tmpCreatedAt;
          final String _tmp_21;
          _tmp_21 = _cursor.getString(_cursorIndexOfCreatedAt);
          _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_21);
          final Instant _tmpUpdatedAt_1;
          final String _tmp_22;
          _tmp_22 = _cursor.getString(_cursorIndexOfUpdatedAt_1);
          _tmpUpdatedAt_1 = __instantRoomTypeConverter.toInstant(_tmp_22);
          final UUID _tmpCreationFacilityUuid;
          final String _tmp_23;
          _tmp_23 = _cursor.getString(_cursorIndexOfCreationFacilityUuid);
          _tmpCreationFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_23);
          _tmpAppointment = new Appointment(_tmpUuid,_tmpPatientUuid,_tmpFacilityUuid,_tmpScheduledDate,_tmpStatus,_tmpCancelReason,_tmpRemindOn,_tmpAgreedToVisit,_tmpAppointmentType,_tmpSyncStatus,_tmpCreatedAt,_tmpUpdatedAt_1,null,_tmpCreationFacilityUuid);
        }  else  {
          _tmpAppointment = null;
        }
        final PatientPhoneNumber _tmpPhoneNumber;
        if (! (_cursor.isNull(_cursorIndexOfUuid_1) && _cursor.isNull(_cursorIndexOfPatientUuid_1) && _cursor.isNull(_cursorIndexOfNumber) && _cursor.isNull(_cursorIndexOfPhoneType) && _cursor.isNull(_cursorIndexOfActive) && _cursor.isNull(_cursorIndexOfCreatedAt_1) && _cursor.isNull(_cursorIndexOfUpdatedAt_2))) {
          final UUID _tmpUuid_1;
          final String _tmp_24;
          _tmp_24 = _cursor.getString(_cursorIndexOfUuid_1);
          _tmpUuid_1 = __uuidRoomTypeConverter.toUuid(_tmp_24);
          final UUID _tmpPatientUuid_1;
          final String _tmp_25;
          _tmp_25 = _cursor.getString(_cursorIndexOfPatientUuid_1);
          _tmpPatientUuid_1 = __uuidRoomTypeConverter.toUuid(_tmp_25);
          final String _tmpNumber;
          _tmpNumber = _cursor.getString(_cursorIndexOfNumber);
          final PatientPhoneNumberType _tmpPhoneType;
          final String _tmp_26;
          _tmp_26 = _cursor.getString(_cursorIndexOfPhoneType);
          _tmpPhoneType = __roomTypeConverter_6.toEnum(_tmp_26);
          final boolean _tmpActive;
          final int _tmp_27;
          _tmp_27 = _cursor.getInt(_cursorIndexOfActive);
          _tmpActive = _tmp_27 != 0;
          final Instant _tmpCreatedAt_1;
          final String _tmp_28;
          _tmp_28 = _cursor.getString(_cursorIndexOfCreatedAt_1);
          _tmpCreatedAt_1 = __instantRoomTypeConverter.toInstant(_tmp_28);
          final Instant _tmpUpdatedAt_2;
          final String _tmp_29;
          _tmp_29 = _cursor.getString(_cursorIndexOfUpdatedAt_2);
          _tmpUpdatedAt_2 = __instantRoomTypeConverter.toInstant(_tmp_29);
          _tmpPhoneNumber = new PatientPhoneNumber(_tmpUuid_1,_tmpPatientUuid_1,_tmpNumber,_tmpPhoneType,_tmpActive,_tmpCreatedAt_1,_tmpUpdatedAt_2,null);
        }  else  {
          _tmpPhoneNumber = null;
        }
        final OverduePatientAddress _tmpPatientAddress;
        if (! (_cursor.isNull(_cursorIndexOfStreetAddress) && _cursor.isNull(_cursorIndexOfColonyOrVillage) && _cursor.isNull(_cursorIndexOfDistrict) && _cursor.isNull(_cursorIndexOfState))) {
          final String _tmpStreetAddress;
          _tmpStreetAddress = _cursor.getString(_cursorIndexOfStreetAddress);
          final String _tmpColonyOrVillage;
          _tmpColonyOrVillage = _cursor.getString(_cursorIndexOfColonyOrVillage);
          final String _tmpDistrict;
          _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
          final String _tmpState;
          _tmpState = _cursor.getString(_cursorIndexOfState);
          _tmpPatientAddress = new OverduePatientAddress(_tmpStreetAddress,_tmpColonyOrVillage,_tmpDistrict,_tmpState);
        }  else  {
          _tmpPatientAddress = null;
        }
        _result = new OverdueAppointment(_tmpFullName,_tmpGender,_tmpDateOfBirth,_tmpAge,_tmpAppointment,_tmpPhoneNumber,_tmpPatientAddress,_tmpIsAtHighRisk,_tmpPatientLastSeen,_tmpDiagnosedWithDiabetes,_tmpDiagnosedWithHypertension,_tmpPatientAssignedFacilityUuid,_tmpAppointmentFacilityName);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
