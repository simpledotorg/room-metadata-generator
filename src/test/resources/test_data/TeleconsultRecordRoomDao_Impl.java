package test_data;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.simple.clinic.patient.SyncStatus;
import org.simple.clinic.storage.Timestamps;
import org.simple.clinic.util.room.InstantRoomTypeConverter;
import org.simple.clinic.util.room.UuidRoomTypeConverter;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TeleconsultRecordRoomDao_Impl implements TeleconsultRecord.RoomDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TeleconsultRecord> __insertionAdapterOfTeleconsultRecord;

  private final UuidRoomTypeConverter __uuidRoomTypeConverter = new UuidRoomTypeConverter();

  private final SyncStatus.RoomTypeConverter __roomTypeConverter = new SyncStatus.RoomTypeConverter();

  private final InstantRoomTypeConverter __instantRoomTypeConverter = new InstantRoomTypeConverter();

  private final TeleconsultStatus.RoomTypeConverter __roomTypeConverter_1 = new TeleconsultStatus.RoomTypeConverter();

  private final TeleconsultationType.RoomTypeConverter __roomTypeConverter_2 = new TeleconsultationType.RoomTypeConverter();

  private final Answer.RoomTypeConverter __roomTypeConverter_3 = new Answer.RoomTypeConverter();

  private final SharedSQLiteStatement __preparedStmtOfClear;

  private final SharedSQLiteStatement __preparedStmtOfUpdateMedicalRegistrationId;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSyncStates;

  private final SharedSQLiteStatement __preparedStmtOfUpdateRequesterCompletionStatus;

  public TeleconsultRecordRoomDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTeleconsultRecord = new EntityInsertionAdapter<TeleconsultRecord>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `TeleconsultRecord` (`id`,`patientId`,`medicalOfficerId`,`syncStatus`,`request_requesterId`,`request_facilityId`,`request_requestedAt`,`request_requesterCompletionStatus`,`record_recordedAt`,`record_teleconsultationType`,`record_patientTookMedicines`,`record_patientConsented`,`record_medicalOfficerNumber`,`createdAt`,`updatedAt`,`deletedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TeleconsultRecord value) {
        final String _tmp;
        _tmp = __uuidRoomTypeConverter.fromUuid(value.getId());
        if (_tmp == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, _tmp);
        }
        final String _tmp_1;
        _tmp_1 = __uuidRoomTypeConverter.fromUuid(value.getPatientId());
        if (_tmp_1 == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp_1);
        }
        final String _tmp_2;
        _tmp_2 = __uuidRoomTypeConverter.fromUuid(value.getMedicalOfficerId());
        if (_tmp_2 == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp_2);
        }
        final String _tmp_3;
        _tmp_3 = __roomTypeConverter.fromEnum(value.getSyncStatus());
        if (_tmp_3 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp_3);
        }
        final TeleconsultRequestInfo _tmpTeleconsultRequestInfo = value.getTeleconsultRequestInfo();
        if(_tmpTeleconsultRequestInfo != null) {
          final String _tmp_4;
          _tmp_4 = __uuidRoomTypeConverter.fromUuid(_tmpTeleconsultRequestInfo.getRequesterId());
          if (_tmp_4 == null) {
            stmt.bindNull(5);
          } else {
            stmt.bindString(5, _tmp_4);
          }
          final String _tmp_5;
          _tmp_5 = __uuidRoomTypeConverter.fromUuid(_tmpTeleconsultRequestInfo.getFacilityId());
          if (_tmp_5 == null) {
            stmt.bindNull(6);
          } else {
            stmt.bindString(6, _tmp_5);
          }
          final String _tmp_6;
          _tmp_6 = __instantRoomTypeConverter.fromInstant(_tmpTeleconsultRequestInfo.getRequestedAt());
          if (_tmp_6 == null) {
            stmt.bindNull(7);
          } else {
            stmt.bindString(7, _tmp_6);
          }
          final String _tmp_7;
          _tmp_7 = __roomTypeConverter_1.fromEnum(_tmpTeleconsultRequestInfo.getRequesterCompletionStatus());
          if (_tmp_7 == null) {
            stmt.bindNull(8);
          } else {
            stmt.bindString(8, _tmp_7);
          }
        } else {
          stmt.bindNull(5);
          stmt.bindNull(6);
          stmt.bindNull(7);
          stmt.bindNull(8);
        }
        final TeleconsultRecordInfo _tmpTeleconsultRecordInfo = value.getTeleconsultRecordInfo();
        if(_tmpTeleconsultRecordInfo != null) {
          final String _tmp_8;
          _tmp_8 = __instantRoomTypeConverter.fromInstant(_tmpTeleconsultRecordInfo.getRecordedAt());
          if (_tmp_8 == null) {
            stmt.bindNull(9);
          } else {
            stmt.bindString(9, _tmp_8);
          }
          final String _tmp_9;
          _tmp_9 = __roomTypeConverter_2.fromEnum(_tmpTeleconsultRecordInfo.getTeleconsultationType());
          if (_tmp_9 == null) {
            stmt.bindNull(10);
          } else {
            stmt.bindString(10, _tmp_9);
          }
          final String _tmp_10;
          _tmp_10 = __roomTypeConverter_3.fromEnum(_tmpTeleconsultRecordInfo.getPatientTookMedicines());
          if (_tmp_10 == null) {
            stmt.bindNull(11);
          } else {
            stmt.bindString(11, _tmp_10);
          }
          final String _tmp_11;
          _tmp_11 = __roomTypeConverter_3.fromEnum(_tmpTeleconsultRecordInfo.getPatientConsented());
          if (_tmp_11 == null) {
            stmt.bindNull(12);
          } else {
            stmt.bindString(12, _tmp_11);
          }
          if (_tmpTeleconsultRecordInfo.getMedicalOfficerNumber() == null) {
            stmt.bindNull(13);
          } else {
            stmt.bindString(13, _tmpTeleconsultRecordInfo.getMedicalOfficerNumber());
          }
        } else {
          stmt.bindNull(9);
          stmt.bindNull(10);
          stmt.bindNull(11);
          stmt.bindNull(12);
          stmt.bindNull(13);
        }
        final Timestamps _tmpTimestamp = value.getTimestamp();
        if(_tmpTimestamp != null) {
          final String _tmp_12;
          _tmp_12 = __instantRoomTypeConverter.fromInstant(_tmpTimestamp.getCreatedAt());
          if (_tmp_12 == null) {
            stmt.bindNull(14);
          } else {
            stmt.bindString(14, _tmp_12);
          }
          final String _tmp_13;
          _tmp_13 = __instantRoomTypeConverter.fromInstant(_tmpTimestamp.getUpdatedAt());
          if (_tmp_13 == null) {
            stmt.bindNull(15);
          } else {
            stmt.bindString(15, _tmp_13);
          }
          final String _tmp_14;
          _tmp_14 = __instantRoomTypeConverter.fromInstant(_tmpTimestamp.getDeletedAt());
          if (_tmp_14 == null) {
            stmt.bindNull(16);
          } else {
            stmt.bindString(16, _tmp_14);
          }
        } else {
          stmt.bindNull(14);
          stmt.bindNull(15);
          stmt.bindNull(16);
        }
      }
    };
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TeleconsultRecord";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateMedicalRegistrationId = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE TeleconsultRecord SET record_medicalOfficerNumber = ?, updatedAt = ?, syncStatus = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSyncStates = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE TeleconsultRecord SET syncStatus = ? WHERE syncStatus = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateRequesterCompletionStatus = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE TeleconsultRecord SET request_requesterCompletionStatus = ?, updatedAt = ?, syncStatus = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void save(final List<TeleconsultRecord> teleconsultRecords) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTeleconsultRecord.insert(teleconsultRecords);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clear() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClear.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClear.release(_stmt);
    }
  }

  @Override
  public void updateMedicalRegistrationId(final UUID teleconsultRecordId,
      final String medicalOfficerNumber, final Instant updatedAt, final SyncStatus syncStatus) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateMedicalRegistrationId.acquire();
    int _argIndex = 1;
    if (medicalOfficerNumber == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, medicalOfficerNumber);
    }
    _argIndex = 2;
    final String _tmp;
    _tmp = __instantRoomTypeConverter.fromInstant(updatedAt);
    if (_tmp == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, _tmp);
    }
    _argIndex = 3;
    final String _tmp_1;
    _tmp_1 = __roomTypeConverter.fromEnum(syncStatus);
    if (_tmp_1 == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, _tmp_1);
    }
    _argIndex = 4;
    final String _tmp_2;
    _tmp_2 = __uuidRoomTypeConverter.fromUuid(teleconsultRecordId);
    if (_tmp_2 == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, _tmp_2);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateMedicalRegistrationId.release(_stmt);
    }
  }

  @Override
  public void updateSyncStates(final SyncStatus oldStatus, final SyncStatus newStatus) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSyncStates.acquire();
    int _argIndex = 1;
    final String _tmp;
    _tmp = __roomTypeConverter.fromEnum(newStatus);
    if (_tmp == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, _tmp);
    }
    _argIndex = 2;
    final String _tmp_1;
    _tmp_1 = __roomTypeConverter.fromEnum(oldStatus);
    if (_tmp_1 == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, _tmp_1);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateSyncStates.release(_stmt);
    }
  }

  @Override
  public void updateRequesterCompletionStatus(final UUID teleconsultRecordId,
      final TeleconsultStatus teleconsultStatus, final Instant updatedAt,
      final SyncStatus syncStatus) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateRequesterCompletionStatus.acquire();
    int _argIndex = 1;
    final String _tmp;
    _tmp = __roomTypeConverter_1.fromEnum(teleconsultStatus);
    if (_tmp == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, _tmp);
    }
    _argIndex = 2;
    final String _tmp_1;
    _tmp_1 = __instantRoomTypeConverter.fromInstant(updatedAt);
    if (_tmp_1 == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, _tmp_1);
    }
    _argIndex = 3;
    final String _tmp_2;
    _tmp_2 = __roomTypeConverter.fromEnum(syncStatus);
    if (_tmp_2 == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, _tmp_2);
    }
    _argIndex = 4;
    final String _tmp_3;
    _tmp_3 = __uuidRoomTypeConverter.fromUuid(teleconsultRecordId);
    if (_tmp_3 == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, _tmp_3);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateRequesterCompletionStatus.release(_stmt);
    }
  }

  @Override
  public List<TeleconsultRecord> getAll() {
    final String _sql = "SELECT * FROM TeleconsultRecord";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
      final int _cursorIndexOfMedicalOfficerId = CursorUtil.getColumnIndexOrThrow(_cursor, "medicalOfficerId");
      final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
      final int _cursorIndexOfRequesterId = CursorUtil.getColumnIndexOrThrow(_cursor, "request_requesterId");
      final int _cursorIndexOfFacilityId = CursorUtil.getColumnIndexOrThrow(_cursor, "request_facilityId");
      final int _cursorIndexOfRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "request_requestedAt");
      final int _cursorIndexOfRequesterCompletionStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "request_requesterCompletionStatus");
      final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "record_recordedAt");
      final int _cursorIndexOfTeleconsultationType = CursorUtil.getColumnIndexOrThrow(_cursor, "record_teleconsultationType");
      final int _cursorIndexOfPatientTookMedicines = CursorUtil.getColumnIndexOrThrow(_cursor, "record_patientTookMedicines");
      final int _cursorIndexOfPatientConsented = CursorUtil.getColumnIndexOrThrow(_cursor, "record_patientConsented");
      final int _cursorIndexOfMedicalOfficerNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "record_medicalOfficerNumber");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
      final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deletedAt");
      final List<TeleconsultRecord> _result = new ArrayList<TeleconsultRecord>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TeleconsultRecord _item;
        final UUID _tmpId;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfId);
        _tmpId = __uuidRoomTypeConverter.toUuid(_tmp);
        final UUID _tmpPatientId;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfPatientId);
        _tmpPatientId = __uuidRoomTypeConverter.toUuid(_tmp_1);
        final UUID _tmpMedicalOfficerId;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfMedicalOfficerId);
        _tmpMedicalOfficerId = __uuidRoomTypeConverter.toUuid(_tmp_2);
        final SyncStatus _tmpSyncStatus;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfSyncStatus);
        _tmpSyncStatus = __roomTypeConverter.toEnum(_tmp_3);
        final TeleconsultRequestInfo _tmpTeleconsultRequestInfo;
        if (! (_cursor.isNull(_cursorIndexOfRequesterId) && _cursor.isNull(_cursorIndexOfFacilityId) && _cursor.isNull(_cursorIndexOfRequestedAt) && _cursor.isNull(_cursorIndexOfRequesterCompletionStatus))) {
          final UUID _tmpRequesterId;
          final String _tmp_4;
          _tmp_4 = _cursor.getString(_cursorIndexOfRequesterId);
          _tmpRequesterId = __uuidRoomTypeConverter.toUuid(_tmp_4);
          final UUID _tmpFacilityId;
          final String _tmp_5;
          _tmp_5 = _cursor.getString(_cursorIndexOfFacilityId);
          _tmpFacilityId = __uuidRoomTypeConverter.toUuid(_tmp_5);
          final Instant _tmpRequestedAt;
          final String _tmp_6;
          _tmp_6 = _cursor.getString(_cursorIndexOfRequestedAt);
          _tmpRequestedAt = __instantRoomTypeConverter.toInstant(_tmp_6);
          final TeleconsultStatus _tmpRequesterCompletionStatus;
          final String _tmp_7;
          _tmp_7 = _cursor.getString(_cursorIndexOfRequesterCompletionStatus);
          _tmpRequesterCompletionStatus = __roomTypeConverter_1.toEnum(_tmp_7);
          _tmpTeleconsultRequestInfo = new TeleconsultRequestInfo(_tmpRequesterId,_tmpFacilityId,_tmpRequestedAt,_tmpRequesterCompletionStatus);
        }  else  {
          _tmpTeleconsultRequestInfo = null;
        }
        final TeleconsultRecordInfo _tmpTeleconsultRecordInfo;
        if (! (_cursor.isNull(_cursorIndexOfRecordedAt) && _cursor.isNull(_cursorIndexOfTeleconsultationType) && _cursor.isNull(_cursorIndexOfPatientTookMedicines) && _cursor.isNull(_cursorIndexOfPatientConsented) && _cursor.isNull(_cursorIndexOfMedicalOfficerNumber))) {
          final Instant _tmpRecordedAt;
          final String _tmp_8;
          _tmp_8 = _cursor.getString(_cursorIndexOfRecordedAt);
          _tmpRecordedAt = __instantRoomTypeConverter.toInstant(_tmp_8);
          final TeleconsultationType _tmpTeleconsultationType;
          final String _tmp_9;
          _tmp_9 = _cursor.getString(_cursorIndexOfTeleconsultationType);
          _tmpTeleconsultationType = __roomTypeConverter_2.toEnum(_tmp_9);
          final Answer _tmpPatientTookMedicines;
          final String _tmp_10;
          _tmp_10 = _cursor.getString(_cursorIndexOfPatientTookMedicines);
          _tmpPatientTookMedicines = __roomTypeConverter_3.toEnum(_tmp_10);
          final Answer _tmpPatientConsented;
          final String _tmp_11;
          _tmp_11 = _cursor.getString(_cursorIndexOfPatientConsented);
          _tmpPatientConsented = __roomTypeConverter_3.toEnum(_tmp_11);
          final String _tmpMedicalOfficerNumber;
          _tmpMedicalOfficerNumber = _cursor.getString(_cursorIndexOfMedicalOfficerNumber);
          _tmpTeleconsultRecordInfo = new TeleconsultRecordInfo(_tmpRecordedAt,_tmpTeleconsultationType,_tmpPatientTookMedicines,_tmpPatientConsented,_tmpMedicalOfficerNumber);
        }  else  {
          _tmpTeleconsultRecordInfo = null;
        }
        final Timestamps _tmpTimestamp;
        if (! (_cursor.isNull(_cursorIndexOfCreatedAt) && _cursor.isNull(_cursorIndexOfUpdatedAt) && _cursor.isNull(_cursorIndexOfDeletedAt))) {
          final Instant _tmpCreatedAt;
          final String _tmp_12;
          _tmp_12 = _cursor.getString(_cursorIndexOfCreatedAt);
          _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_12);
          final Instant _tmpUpdatedAt;
          final String _tmp_13;
          _tmp_13 = _cursor.getString(_cursorIndexOfUpdatedAt);
          _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_13);
          final Instant _tmpDeletedAt;
          final String _tmp_14;
          _tmp_14 = _cursor.getString(_cursorIndexOfDeletedAt);
          _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_14);
          _tmpTimestamp = new Timestamps(_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt);
        }  else  {
          _tmpTimestamp = null;
        }
        _item = new TeleconsultRecord(_tmpId,_tmpPatientId,_tmpMedicalOfficerId,_tmpTeleconsultRequestInfo,_tmpTeleconsultRecordInfo,_tmpTimestamp,_tmpSyncStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public TeleconsultRecord getCompleteTeleconsultLog(final UUID teleconsultRecordId) {
    final String _sql = "SELECT * FROM TeleconsultRecord WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __uuidRoomTypeConverter.fromUuid(teleconsultRecordId);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
      final int _cursorIndexOfMedicalOfficerId = CursorUtil.getColumnIndexOrThrow(_cursor, "medicalOfficerId");
      final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
      final int _cursorIndexOfRequesterId = CursorUtil.getColumnIndexOrThrow(_cursor, "request_requesterId");
      final int _cursorIndexOfFacilityId = CursorUtil.getColumnIndexOrThrow(_cursor, "request_facilityId");
      final int _cursorIndexOfRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "request_requestedAt");
      final int _cursorIndexOfRequesterCompletionStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "request_requesterCompletionStatus");
      final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "record_recordedAt");
      final int _cursorIndexOfTeleconsultationType = CursorUtil.getColumnIndexOrThrow(_cursor, "record_teleconsultationType");
      final int _cursorIndexOfPatientTookMedicines = CursorUtil.getColumnIndexOrThrow(_cursor, "record_patientTookMedicines");
      final int _cursorIndexOfPatientConsented = CursorUtil.getColumnIndexOrThrow(_cursor, "record_patientConsented");
      final int _cursorIndexOfMedicalOfficerNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "record_medicalOfficerNumber");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
      final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deletedAt");
      final TeleconsultRecord _result;
      if(_cursor.moveToFirst()) {
        final UUID _tmpId;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfId);
        _tmpId = __uuidRoomTypeConverter.toUuid(_tmp_1);
        final UUID _tmpPatientId;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfPatientId);
        _tmpPatientId = __uuidRoomTypeConverter.toUuid(_tmp_2);
        final UUID _tmpMedicalOfficerId;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfMedicalOfficerId);
        _tmpMedicalOfficerId = __uuidRoomTypeConverter.toUuid(_tmp_3);
        final SyncStatus _tmpSyncStatus;
        final String _tmp_4;
        _tmp_4 = _cursor.getString(_cursorIndexOfSyncStatus);
        _tmpSyncStatus = __roomTypeConverter.toEnum(_tmp_4);
        final TeleconsultRequestInfo _tmpTeleconsultRequestInfo;
        if (! (_cursor.isNull(_cursorIndexOfRequesterId) && _cursor.isNull(_cursorIndexOfFacilityId) && _cursor.isNull(_cursorIndexOfRequestedAt) && _cursor.isNull(_cursorIndexOfRequesterCompletionStatus))) {
          final UUID _tmpRequesterId;
          final String _tmp_5;
          _tmp_5 = _cursor.getString(_cursorIndexOfRequesterId);
          _tmpRequesterId = __uuidRoomTypeConverter.toUuid(_tmp_5);
          final UUID _tmpFacilityId;
          final String _tmp_6;
          _tmp_6 = _cursor.getString(_cursorIndexOfFacilityId);
          _tmpFacilityId = __uuidRoomTypeConverter.toUuid(_tmp_6);
          final Instant _tmpRequestedAt;
          final String _tmp_7;
          _tmp_7 = _cursor.getString(_cursorIndexOfRequestedAt);
          _tmpRequestedAt = __instantRoomTypeConverter.toInstant(_tmp_7);
          final TeleconsultStatus _tmpRequesterCompletionStatus;
          final String _tmp_8;
          _tmp_8 = _cursor.getString(_cursorIndexOfRequesterCompletionStatus);
          _tmpRequesterCompletionStatus = __roomTypeConverter_1.toEnum(_tmp_8);
          _tmpTeleconsultRequestInfo = new TeleconsultRequestInfo(_tmpRequesterId,_tmpFacilityId,_tmpRequestedAt,_tmpRequesterCompletionStatus);
        }  else  {
          _tmpTeleconsultRequestInfo = null;
        }
        final TeleconsultRecordInfo _tmpTeleconsultRecordInfo;
        if (! (_cursor.isNull(_cursorIndexOfRecordedAt) && _cursor.isNull(_cursorIndexOfTeleconsultationType) && _cursor.isNull(_cursorIndexOfPatientTookMedicines) && _cursor.isNull(_cursorIndexOfPatientConsented) && _cursor.isNull(_cursorIndexOfMedicalOfficerNumber))) {
          final Instant _tmpRecordedAt;
          final String _tmp_9;
          _tmp_9 = _cursor.getString(_cursorIndexOfRecordedAt);
          _tmpRecordedAt = __instantRoomTypeConverter.toInstant(_tmp_9);
          final TeleconsultationType _tmpTeleconsultationType;
          final String _tmp_10;
          _tmp_10 = _cursor.getString(_cursorIndexOfTeleconsultationType);
          _tmpTeleconsultationType = __roomTypeConverter_2.toEnum(_tmp_10);
          final Answer _tmpPatientTookMedicines;
          final String _tmp_11;
          _tmp_11 = _cursor.getString(_cursorIndexOfPatientTookMedicines);
          _tmpPatientTookMedicines = __roomTypeConverter_3.toEnum(_tmp_11);
          final Answer _tmpPatientConsented;
          final String _tmp_12;
          _tmp_12 = _cursor.getString(_cursorIndexOfPatientConsented);
          _tmpPatientConsented = __roomTypeConverter_3.toEnum(_tmp_12);
          final String _tmpMedicalOfficerNumber;
          _tmpMedicalOfficerNumber = _cursor.getString(_cursorIndexOfMedicalOfficerNumber);
          _tmpTeleconsultRecordInfo = new TeleconsultRecordInfo(_tmpRecordedAt,_tmpTeleconsultationType,_tmpPatientTookMedicines,_tmpPatientConsented,_tmpMedicalOfficerNumber);
        }  else  {
          _tmpTeleconsultRecordInfo = null;
        }
        final Timestamps _tmpTimestamp;
        if (! (_cursor.isNull(_cursorIndexOfCreatedAt) && _cursor.isNull(_cursorIndexOfUpdatedAt) && _cursor.isNull(_cursorIndexOfDeletedAt))) {
          final Instant _tmpCreatedAt;
          final String _tmp_13;
          _tmp_13 = _cursor.getString(_cursorIndexOfCreatedAt);
          _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_13);
          final Instant _tmpUpdatedAt;
          final String _tmp_14;
          _tmp_14 = _cursor.getString(_cursorIndexOfUpdatedAt);
          _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_14);
          final Instant _tmpDeletedAt;
          final String _tmp_15;
          _tmp_15 = _cursor.getString(_cursorIndexOfDeletedAt);
          _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_15);
          _tmpTimestamp = new Timestamps(_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt);
        }  else  {
          _tmpTimestamp = null;
        }
        _result = new TeleconsultRecord(_tmpId,_tmpPatientId,_tmpMedicalOfficerId,_tmpTeleconsultRequestInfo,_tmpTeleconsultRecordInfo,_tmpTimestamp,_tmpSyncStatus);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TeleconsultRecord> recordsWithSyncStatus(final SyncStatus syncStatus) {
    final String _sql = "SELECT * FROM TeleconsultRecord WHERE syncStatus = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __roomTypeConverter.fromEnum(syncStatus);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
      final int _cursorIndexOfMedicalOfficerId = CursorUtil.getColumnIndexOrThrow(_cursor, "medicalOfficerId");
      final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
      final int _cursorIndexOfRequesterId = CursorUtil.getColumnIndexOrThrow(_cursor, "request_requesterId");
      final int _cursorIndexOfFacilityId = CursorUtil.getColumnIndexOrThrow(_cursor, "request_facilityId");
      final int _cursorIndexOfRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "request_requestedAt");
      final int _cursorIndexOfRequesterCompletionStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "request_requesterCompletionStatus");
      final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "record_recordedAt");
      final int _cursorIndexOfTeleconsultationType = CursorUtil.getColumnIndexOrThrow(_cursor, "record_teleconsultationType");
      final int _cursorIndexOfPatientTookMedicines = CursorUtil.getColumnIndexOrThrow(_cursor, "record_patientTookMedicines");
      final int _cursorIndexOfPatientConsented = CursorUtil.getColumnIndexOrThrow(_cursor, "record_patientConsented");
      final int _cursorIndexOfMedicalOfficerNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "record_medicalOfficerNumber");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
      final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deletedAt");
      final List<TeleconsultRecord> _result = new ArrayList<TeleconsultRecord>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TeleconsultRecord _item;
        final UUID _tmpId;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfId);
        _tmpId = __uuidRoomTypeConverter.toUuid(_tmp_1);
        final UUID _tmpPatientId;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfPatientId);
        _tmpPatientId = __uuidRoomTypeConverter.toUuid(_tmp_2);
        final UUID _tmpMedicalOfficerId;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfMedicalOfficerId);
        _tmpMedicalOfficerId = __uuidRoomTypeConverter.toUuid(_tmp_3);
        final SyncStatus _tmpSyncStatus;
        final String _tmp_4;
        _tmp_4 = _cursor.getString(_cursorIndexOfSyncStatus);
        _tmpSyncStatus = __roomTypeConverter.toEnum(_tmp_4);
        final TeleconsultRequestInfo _tmpTeleconsultRequestInfo;
        if (! (_cursor.isNull(_cursorIndexOfRequesterId) && _cursor.isNull(_cursorIndexOfFacilityId) && _cursor.isNull(_cursorIndexOfRequestedAt) && _cursor.isNull(_cursorIndexOfRequesterCompletionStatus))) {
          final UUID _tmpRequesterId;
          final String _tmp_5;
          _tmp_5 = _cursor.getString(_cursorIndexOfRequesterId);
          _tmpRequesterId = __uuidRoomTypeConverter.toUuid(_tmp_5);
          final UUID _tmpFacilityId;
          final String _tmp_6;
          _tmp_6 = _cursor.getString(_cursorIndexOfFacilityId);
          _tmpFacilityId = __uuidRoomTypeConverter.toUuid(_tmp_6);
          final Instant _tmpRequestedAt;
          final String _tmp_7;
          _tmp_7 = _cursor.getString(_cursorIndexOfRequestedAt);
          _tmpRequestedAt = __instantRoomTypeConverter.toInstant(_tmp_7);
          final TeleconsultStatus _tmpRequesterCompletionStatus;
          final String _tmp_8;
          _tmp_8 = _cursor.getString(_cursorIndexOfRequesterCompletionStatus);
          _tmpRequesterCompletionStatus = __roomTypeConverter_1.toEnum(_tmp_8);
          _tmpTeleconsultRequestInfo = new TeleconsultRequestInfo(_tmpRequesterId,_tmpFacilityId,_tmpRequestedAt,_tmpRequesterCompletionStatus);
        }  else  {
          _tmpTeleconsultRequestInfo = null;
        }
        final TeleconsultRecordInfo _tmpTeleconsultRecordInfo;
        if (! (_cursor.isNull(_cursorIndexOfRecordedAt) && _cursor.isNull(_cursorIndexOfTeleconsultationType) && _cursor.isNull(_cursorIndexOfPatientTookMedicines) && _cursor.isNull(_cursorIndexOfPatientConsented) && _cursor.isNull(_cursorIndexOfMedicalOfficerNumber))) {
          final Instant _tmpRecordedAt;
          final String _tmp_9;
          _tmp_9 = _cursor.getString(_cursorIndexOfRecordedAt);
          _tmpRecordedAt = __instantRoomTypeConverter.toInstant(_tmp_9);
          final TeleconsultationType _tmpTeleconsultationType;
          final String _tmp_10;
          _tmp_10 = _cursor.getString(_cursorIndexOfTeleconsultationType);
          _tmpTeleconsultationType = __roomTypeConverter_2.toEnum(_tmp_10);
          final Answer _tmpPatientTookMedicines;
          final String _tmp_11;
          _tmp_11 = _cursor.getString(_cursorIndexOfPatientTookMedicines);
          _tmpPatientTookMedicines = __roomTypeConverter_3.toEnum(_tmp_11);
          final Answer _tmpPatientConsented;
          final String _tmp_12;
          _tmp_12 = _cursor.getString(_cursorIndexOfPatientConsented);
          _tmpPatientConsented = __roomTypeConverter_3.toEnum(_tmp_12);
          final String _tmpMedicalOfficerNumber;
          _tmpMedicalOfficerNumber = _cursor.getString(_cursorIndexOfMedicalOfficerNumber);
          _tmpTeleconsultRecordInfo = new TeleconsultRecordInfo(_tmpRecordedAt,_tmpTeleconsultationType,_tmpPatientTookMedicines,_tmpPatientConsented,_tmpMedicalOfficerNumber);
        }  else  {
          _tmpTeleconsultRecordInfo = null;
        }
        final Timestamps _tmpTimestamp;
        if (! (_cursor.isNull(_cursorIndexOfCreatedAt) && _cursor.isNull(_cursorIndexOfUpdatedAt) && _cursor.isNull(_cursorIndexOfDeletedAt))) {
          final Instant _tmpCreatedAt;
          final String _tmp_13;
          _tmp_13 = _cursor.getString(_cursorIndexOfCreatedAt);
          _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_13);
          final Instant _tmpUpdatedAt;
          final String _tmp_14;
          _tmp_14 = _cursor.getString(_cursorIndexOfUpdatedAt);
          _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_14);
          final Instant _tmpDeletedAt;
          final String _tmp_15;
          _tmp_15 = _cursor.getString(_cursorIndexOfDeletedAt);
          _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_15);
          _tmpTimestamp = new Timestamps(_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt);
        }  else  {
          _tmpTimestamp = null;
        }
        _item = new TeleconsultRecord(_tmpId,_tmpPatientId,_tmpMedicalOfficerId,_tmpTeleconsultRequestInfo,_tmpTeleconsultRecordInfo,_tmpTimestamp,_tmpSyncStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Flowable<Integer> count() {
    final String _sql = "SELECT COUNT(id) FROM TeleconsultRecord";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[]{"TeleconsultRecord"}, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
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
  public Flowable<Integer> count(final SyncStatus syncStatus) {
    final String _sql = "SELECT COUNT(id) FROM TeleconsultRecord WHERE syncStatus = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __roomTypeConverter.fromEnum(syncStatus);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return RxRoom.createFlowable(__db, false, new String[]{"TeleconsultRecord"}, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp_1;
            if (_cursor.isNull(0)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getInt(0);
            }
            _result = _tmp_1;
          } else {
            _result = null;
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
  public List<UUID> recordIdsWithSyncStatus(final SyncStatus syncStatus) {
    final String _sql = "SELECT id FROM TeleconsultRecord WHERE syncStatus = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __roomTypeConverter.fromEnum(syncStatus);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<UUID> _result = new ArrayList<UUID>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final UUID _item;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(0);
        _item = __uuidRoomTypeConverter.toUuid(_tmp_1);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public TeleconsultRecord latestTeleconsultRecord(final UUID patientUuid) {
    final String _sql = "\n"
            + "      SELECT * FROM TeleconsultRecord\n"
            + "      WHERE patientId = ? AND deletedAt IS NULL\n"
            + "      ORDER BY createdAt DESC LIMIT 1\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __uuidRoomTypeConverter.fromUuid(patientUuid);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
      final int _cursorIndexOfMedicalOfficerId = CursorUtil.getColumnIndexOrThrow(_cursor, "medicalOfficerId");
      final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
      final int _cursorIndexOfRequesterId = CursorUtil.getColumnIndexOrThrow(_cursor, "request_requesterId");
      final int _cursorIndexOfFacilityId = CursorUtil.getColumnIndexOrThrow(_cursor, "request_facilityId");
      final int _cursorIndexOfRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "request_requestedAt");
      final int _cursorIndexOfRequesterCompletionStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "request_requesterCompletionStatus");
      final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "record_recordedAt");
      final int _cursorIndexOfTeleconsultationType = CursorUtil.getColumnIndexOrThrow(_cursor, "record_teleconsultationType");
      final int _cursorIndexOfPatientTookMedicines = CursorUtil.getColumnIndexOrThrow(_cursor, "record_patientTookMedicines");
      final int _cursorIndexOfPatientConsented = CursorUtil.getColumnIndexOrThrow(_cursor, "record_patientConsented");
      final int _cursorIndexOfMedicalOfficerNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "record_medicalOfficerNumber");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
      final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deletedAt");
      final TeleconsultRecord _result;
      if(_cursor.moveToFirst()) {
        final UUID _tmpId;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfId);
        _tmpId = __uuidRoomTypeConverter.toUuid(_tmp_1);
        final UUID _tmpPatientId;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfPatientId);
        _tmpPatientId = __uuidRoomTypeConverter.toUuid(_tmp_2);
        final UUID _tmpMedicalOfficerId;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfMedicalOfficerId);
        _tmpMedicalOfficerId = __uuidRoomTypeConverter.toUuid(_tmp_3);
        final SyncStatus _tmpSyncStatus;
        final String _tmp_4;
        _tmp_4 = _cursor.getString(_cursorIndexOfSyncStatus);
        _tmpSyncStatus = __roomTypeConverter.toEnum(_tmp_4);
        final TeleconsultRequestInfo _tmpTeleconsultRequestInfo;
        if (! (_cursor.isNull(_cursorIndexOfRequesterId) && _cursor.isNull(_cursorIndexOfFacilityId) && _cursor.isNull(_cursorIndexOfRequestedAt) && _cursor.isNull(_cursorIndexOfRequesterCompletionStatus))) {
          final UUID _tmpRequesterId;
          final String _tmp_5;
          _tmp_5 = _cursor.getString(_cursorIndexOfRequesterId);
          _tmpRequesterId = __uuidRoomTypeConverter.toUuid(_tmp_5);
          final UUID _tmpFacilityId;
          final String _tmp_6;
          _tmp_6 = _cursor.getString(_cursorIndexOfFacilityId);
          _tmpFacilityId = __uuidRoomTypeConverter.toUuid(_tmp_6);
          final Instant _tmpRequestedAt;
          final String _tmp_7;
          _tmp_7 = _cursor.getString(_cursorIndexOfRequestedAt);
          _tmpRequestedAt = __instantRoomTypeConverter.toInstant(_tmp_7);
          final TeleconsultStatus _tmpRequesterCompletionStatus;
          final String _tmp_8;
          _tmp_8 = _cursor.getString(_cursorIndexOfRequesterCompletionStatus);
          _tmpRequesterCompletionStatus = __roomTypeConverter_1.toEnum(_tmp_8);
          _tmpTeleconsultRequestInfo = new TeleconsultRequestInfo(_tmpRequesterId,_tmpFacilityId,_tmpRequestedAt,_tmpRequesterCompletionStatus);
        }  else  {
          _tmpTeleconsultRequestInfo = null;
        }
        final TeleconsultRecordInfo _tmpTeleconsultRecordInfo;
        if (! (_cursor.isNull(_cursorIndexOfRecordedAt) && _cursor.isNull(_cursorIndexOfTeleconsultationType) && _cursor.isNull(_cursorIndexOfPatientTookMedicines) && _cursor.isNull(_cursorIndexOfPatientConsented) && _cursor.isNull(_cursorIndexOfMedicalOfficerNumber))) {
          final Instant _tmpRecordedAt;
          final String _tmp_9;
          _tmp_9 = _cursor.getString(_cursorIndexOfRecordedAt);
          _tmpRecordedAt = __instantRoomTypeConverter.toInstant(_tmp_9);
          final TeleconsultationType _tmpTeleconsultationType;
          final String _tmp_10;
          _tmp_10 = _cursor.getString(_cursorIndexOfTeleconsultationType);
          _tmpTeleconsultationType = __roomTypeConverter_2.toEnum(_tmp_10);
          final Answer _tmpPatientTookMedicines;
          final String _tmp_11;
          _tmp_11 = _cursor.getString(_cursorIndexOfPatientTookMedicines);
          _tmpPatientTookMedicines = __roomTypeConverter_3.toEnum(_tmp_11);
          final Answer _tmpPatientConsented;
          final String _tmp_12;
          _tmp_12 = _cursor.getString(_cursorIndexOfPatientConsented);
          _tmpPatientConsented = __roomTypeConverter_3.toEnum(_tmp_12);
          final String _tmpMedicalOfficerNumber;
          _tmpMedicalOfficerNumber = _cursor.getString(_cursorIndexOfMedicalOfficerNumber);
          _tmpTeleconsultRecordInfo = new TeleconsultRecordInfo(_tmpRecordedAt,_tmpTeleconsultationType,_tmpPatientTookMedicines,_tmpPatientConsented,_tmpMedicalOfficerNumber);
        }  else  {
          _tmpTeleconsultRecordInfo = null;
        }
        final Timestamps _tmpTimestamp;
        if (! (_cursor.isNull(_cursorIndexOfCreatedAt) && _cursor.isNull(_cursorIndexOfUpdatedAt) && _cursor.isNull(_cursorIndexOfDeletedAt))) {
          final Instant _tmpCreatedAt;
          final String _tmp_13;
          _tmp_13 = _cursor.getString(_cursorIndexOfCreatedAt);
          _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_13);
          final Instant _tmpUpdatedAt;
          final String _tmp_14;
          _tmp_14 = _cursor.getString(_cursorIndexOfUpdatedAt);
          _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_14);
          final Instant _tmpDeletedAt;
          final String _tmp_15;
          _tmp_15 = _cursor.getString(_cursorIndexOfDeletedAt);
          _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_15);
          _tmpTimestamp = new Timestamps(_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt);
        }  else  {
          _tmpTimestamp = null;
        }
        _result = new TeleconsultRecord(_tmpId,_tmpPatientId,_tmpMedicalOfficerId,_tmpTeleconsultRequestInfo,_tmpTeleconsultRecordInfo,_tmpTimestamp,_tmpSyncStatus);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public void updateSyncStatus(final List<UUID> uuids, final SyncStatus newStatus) {
    __db.assertNotSuspendingTransaction();
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("UPDATE TeleconsultRecord SET syncStatus = ");
    _stringBuilder.append("?");
    _stringBuilder.append(" WHERE id in (");
    final int _inputSize = uuids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") ");
    final String _sql = _stringBuilder.toString();
    final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __roomTypeConverter.fromEnum(newStatus);
    if (_tmp == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, _tmp);
    }
    _argIndex = 2;
    for (UUID _item : uuids) {
      final String _tmp_1;
      _tmp_1 = __uuidRoomTypeConverter.fromUuid(_item);
      if (_tmp_1 == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _tmp_1);
      }
      _argIndex ++;
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
