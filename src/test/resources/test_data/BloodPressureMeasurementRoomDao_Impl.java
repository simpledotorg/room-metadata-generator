package test_data;

import android.database.Cursor;
import androidx.paging.DataSource;
import androidx.paging.DataSource.Factory;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.reactivex.Flowable;
import io.reactivex.Observable;
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
import org.simple.clinic.util.room.InstantRoomTypeConverter;
import org.simple.clinic.util.room.UuidRoomTypeConverter;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BloodPressureMeasurementRoomDao_Impl implements BloodPressureMeasurement.RoomDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BloodPressureMeasurement> __insertionAdapterOfBloodPressureMeasurement;

  private final UuidRoomTypeConverter __uuidRoomTypeConverter = new UuidRoomTypeConverter();

  private final SyncStatus.RoomTypeConverter __roomTypeConverter = new SyncStatus.RoomTypeConverter();

  private final InstantRoomTypeConverter __instantRoomTypeConverter = new InstantRoomTypeConverter();

  private final SharedSQLiteStatement __preparedStmtOfUpdateSyncStatus;

  private final SharedSQLiteStatement __preparedStmtOfClearData;

  private final SharedSQLiteStatement __preparedStmtOfPurgeDeleted;

  private final SharedSQLiteStatement __preparedStmtOfDeleteWithoutLinkedPatient;

  public BloodPressureMeasurementRoomDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBloodPressureMeasurement = new EntityInsertionAdapter<BloodPressureMeasurement>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `BloodPressureMeasurement` (`uuid`,`syncStatus`,`userUuid`,`facilityUuid`,`patientUuid`,`createdAt`,`updatedAt`,`deletedAt`,`recordedAt`,`systolic`,`diastolic`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BloodPressureMeasurement value) {
        final String _tmp;
        _tmp = __uuidRoomTypeConverter.fromUuid(value.getUuid());
        if (_tmp == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, _tmp);
        }
        final String _tmp_1;
        _tmp_1 = __roomTypeConverter.fromEnum(value.getSyncStatus());
        if (_tmp_1 == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp_1);
        }
        final String _tmp_2;
        _tmp_2 = __uuidRoomTypeConverter.fromUuid(value.getUserUuid());
        if (_tmp_2 == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp_2);
        }
        final String _tmp_3;
        _tmp_3 = __uuidRoomTypeConverter.fromUuid(value.getFacilityUuid());
        if (_tmp_3 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp_3);
        }
        final String _tmp_4;
        _tmp_4 = __uuidRoomTypeConverter.fromUuid(value.getPatientUuid());
        if (_tmp_4 == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, _tmp_4);
        }
        final String _tmp_5;
        _tmp_5 = __instantRoomTypeConverter.fromInstant(value.getCreatedAt());
        if (_tmp_5 == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, _tmp_5);
        }
        final String _tmp_6;
        _tmp_6 = __instantRoomTypeConverter.fromInstant(value.getUpdatedAt());
        if (_tmp_6 == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, _tmp_6);
        }
        final String _tmp_7;
        _tmp_7 = __instantRoomTypeConverter.fromInstant(value.getDeletedAt());
        if (_tmp_7 == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, _tmp_7);
        }
        final String _tmp_8;
        _tmp_8 = __instantRoomTypeConverter.fromInstant(value.getRecordedAt());
        if (_tmp_8 == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, _tmp_8);
        }
        final BloodPressureReading _tmpReading = value.getReading();
        if(_tmpReading != null) {
          stmt.bindLong(10, _tmpReading.getSystolic());
          stmt.bindLong(11, _tmpReading.getDiastolic());
        } else {
          stmt.bindNull(10);
          stmt.bindNull(11);
        }
      }
    };
    this.__preparedStmtOfUpdateSyncStatus = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE bloodpressuremeasurement SET syncStatus = ? WHERE syncStatus = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM bloodpressuremeasurement";
        return _query;
      }
    };
    this.__preparedStmtOfPurgeDeleted = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "\n"
                + "      DELETE FROM BloodPressureMeasurement\n"
                + "      WHERE deletedAt IS NOT NULL AND syncStatus == 'DONE'\n"
                + "    ";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteWithoutLinkedPatient = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "\n"
                + "        DELETE FROM BloodPressureMeasurement\n"
                + "        WHERE \n"
                + "            uuid NOT IN (\n"
                + "                SELECT BP.uuid FROM BloodPressureMeasurement BP\n"
                + "                INNER JOIN Patient P ON P.uuid == BP.patientUuid\n"
                + "            ) AND\n"
                + "            syncStatus == 'DONE'\n"
                + "    ";
        return _query;
      }
    };
  }

  @Override
  public void save(final List<BloodPressureMeasurement> newMeasurements) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBloodPressureMeasurement.insert(newMeasurements);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateSyncStatus(final SyncStatus oldStatus, final SyncStatus newStatus) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSyncStatus.acquire();
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
      __preparedStmtOfUpdateSyncStatus.release(_stmt);
    }
  }

  @Override
  public int clearData() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearData.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfClearData.release(_stmt);
    }
  }

  @Override
  public void purgeDeleted() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfPurgeDeleted.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfPurgeDeleted.release(_stmt);
    }
  }

  @Override
  public void deleteWithoutLinkedPatient() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteWithoutLinkedPatient.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteWithoutLinkedPatient.release(_stmt);
    }
  }

  @Override
  public List<BloodPressureMeasurement> withSyncStatus(final SyncStatus status) {
    final String _sql = "SELECT * FROM bloodpressuremeasurement WHERE syncStatus = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __roomTypeConverter.fromEnum(status);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
      final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
      final int _cursorIndexOfUserUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "userUuid");
      final int _cursorIndexOfFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "facilityUuid");
      final int _cursorIndexOfPatientUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "patientUuid");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
      final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deletedAt");
      final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
      final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
      final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
      final List<BloodPressureMeasurement> _result = new ArrayList<BloodPressureMeasurement>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final BloodPressureMeasurement _item;
        final UUID _tmpUuid;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfUuid);
        _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp_1);
        final SyncStatus _tmpSyncStatus;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfSyncStatus);
        _tmpSyncStatus = __roomTypeConverter.toEnum(_tmp_2);
        final UUID _tmpUserUuid;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfUserUuid);
        _tmpUserUuid = __uuidRoomTypeConverter.toUuid(_tmp_3);
        final UUID _tmpFacilityUuid;
        final String _tmp_4;
        _tmp_4 = _cursor.getString(_cursorIndexOfFacilityUuid);
        _tmpFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_4);
        final UUID _tmpPatientUuid;
        final String _tmp_5;
        _tmp_5 = _cursor.getString(_cursorIndexOfPatientUuid);
        _tmpPatientUuid = __uuidRoomTypeConverter.toUuid(_tmp_5);
        final Instant _tmpCreatedAt;
        final String _tmp_6;
        _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
        _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_6);
        final Instant _tmpUpdatedAt;
        final String _tmp_7;
        _tmp_7 = _cursor.getString(_cursorIndexOfUpdatedAt);
        _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_7);
        final Instant _tmpDeletedAt;
        final String _tmp_8;
        _tmp_8 = _cursor.getString(_cursorIndexOfDeletedAt);
        _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_8);
        final Instant _tmpRecordedAt;
        final String _tmp_9;
        _tmp_9 = _cursor.getString(_cursorIndexOfRecordedAt);
        _tmpRecordedAt = __instantRoomTypeConverter.toInstant(_tmp_9);
        final BloodPressureReading _tmpReading;
        if (! (_cursor.isNull(_cursorIndexOfSystolic) && _cursor.isNull(_cursorIndexOfDiastolic))) {
          final int _tmpSystolic;
          _tmpSystolic = _cursor.getInt(_cursorIndexOfSystolic);
          final int _tmpDiastolic;
          _tmpDiastolic = _cursor.getInt(_cursorIndexOfDiastolic);
          _tmpReading = new BloodPressureReading(_tmpSystolic,_tmpDiastolic);
        }  else  {
          _tmpReading = null;
        }
        _item = new BloodPressureMeasurement(_tmpUuid,_tmpReading,_tmpSyncStatus,_tmpUserUuid,_tmpFacilityUuid,_tmpPatientUuid,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpRecordedAt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public BloodPressureMeasurement getOne(final UUID uuid) {
    final String _sql = "SELECT * FROM bloodpressuremeasurement WHERE uuid = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __uuidRoomTypeConverter.fromUuid(uuid);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
      final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
      final int _cursorIndexOfUserUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "userUuid");
      final int _cursorIndexOfFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "facilityUuid");
      final int _cursorIndexOfPatientUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "patientUuid");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
      final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deletedAt");
      final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
      final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
      final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
      final BloodPressureMeasurement _result;
      if(_cursor.moveToFirst()) {
        final UUID _tmpUuid;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfUuid);
        _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp_1);
        final SyncStatus _tmpSyncStatus;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfSyncStatus);
        _tmpSyncStatus = __roomTypeConverter.toEnum(_tmp_2);
        final UUID _tmpUserUuid;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfUserUuid);
        _tmpUserUuid = __uuidRoomTypeConverter.toUuid(_tmp_3);
        final UUID _tmpFacilityUuid;
        final String _tmp_4;
        _tmp_4 = _cursor.getString(_cursorIndexOfFacilityUuid);
        _tmpFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_4);
        final UUID _tmpPatientUuid;
        final String _tmp_5;
        _tmp_5 = _cursor.getString(_cursorIndexOfPatientUuid);
        _tmpPatientUuid = __uuidRoomTypeConverter.toUuid(_tmp_5);
        final Instant _tmpCreatedAt;
        final String _tmp_6;
        _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
        _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_6);
        final Instant _tmpUpdatedAt;
        final String _tmp_7;
        _tmp_7 = _cursor.getString(_cursorIndexOfUpdatedAt);
        _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_7);
        final Instant _tmpDeletedAt;
        final String _tmp_8;
        _tmp_8 = _cursor.getString(_cursorIndexOfDeletedAt);
        _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_8);
        final Instant _tmpRecordedAt;
        final String _tmp_9;
        _tmp_9 = _cursor.getString(_cursorIndexOfRecordedAt);
        _tmpRecordedAt = __instantRoomTypeConverter.toInstant(_tmp_9);
        final BloodPressureReading _tmpReading;
        if (! (_cursor.isNull(_cursorIndexOfSystolic) && _cursor.isNull(_cursorIndexOfDiastolic))) {
          final int _tmpSystolic;
          _tmpSystolic = _cursor.getInt(_cursorIndexOfSystolic);
          final int _tmpDiastolic;
          _tmpDiastolic = _cursor.getInt(_cursorIndexOfDiastolic);
          _tmpReading = new BloodPressureReading(_tmpSystolic,_tmpDiastolic);
        }  else  {
          _tmpReading = null;
        }
        _result = new BloodPressureMeasurement(_tmpUuid,_tmpReading,_tmpSyncStatus,_tmpUserUuid,_tmpFacilityUuid,_tmpPatientUuid,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpRecordedAt);
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
  public List<UUID> recordIdsWithStatus(final SyncStatus syncStatus) {
    final String _sql = "SELECT uuid FROM bloodpressuremeasurement WHERE syncStatus = ?";
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
  public Flowable<BloodPressureMeasurement> bloodPressure(final UUID uuid) {
    final String _sql = "SELECT * FROM bloodpressuremeasurement WHERE uuid = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __uuidRoomTypeConverter.fromUuid(uuid);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return RxRoom.createFlowable(__db, false, new String[]{"bloodpressuremeasurement"}, new Callable<BloodPressureMeasurement>() {
      @Override
      public BloodPressureMeasurement call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
          final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
          final int _cursorIndexOfUserUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "userUuid");
          final int _cursorIndexOfFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "facilityUuid");
          final int _cursorIndexOfPatientUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "patientUuid");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deletedAt");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
          final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
          final BloodPressureMeasurement _result;
          if(_cursor.moveToFirst()) {
            final UUID _tmpUuid;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfUuid);
            _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp_1);
            final SyncStatus _tmpSyncStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfSyncStatus);
            _tmpSyncStatus = __roomTypeConverter.toEnum(_tmp_2);
            final UUID _tmpUserUuid;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfUserUuid);
            _tmpUserUuid = __uuidRoomTypeConverter.toUuid(_tmp_3);
            final UUID _tmpFacilityUuid;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfFacilityUuid);
            _tmpFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_4);
            final UUID _tmpPatientUuid;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfPatientUuid);
            _tmpPatientUuid = __uuidRoomTypeConverter.toUuid(_tmp_5);
            final Instant _tmpCreatedAt;
            final String _tmp_6;
            _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
            _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_6);
            final Instant _tmpUpdatedAt;
            final String _tmp_7;
            _tmp_7 = _cursor.getString(_cursorIndexOfUpdatedAt);
            _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_7);
            final Instant _tmpDeletedAt;
            final String _tmp_8;
            _tmp_8 = _cursor.getString(_cursorIndexOfDeletedAt);
            _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_8);
            final Instant _tmpRecordedAt;
            final String _tmp_9;
            _tmp_9 = _cursor.getString(_cursorIndexOfRecordedAt);
            _tmpRecordedAt = __instantRoomTypeConverter.toInstant(_tmp_9);
            final BloodPressureReading _tmpReading;
            if (! (_cursor.isNull(_cursorIndexOfSystolic) && _cursor.isNull(_cursorIndexOfDiastolic))) {
              final int _tmpSystolic;
              _tmpSystolic = _cursor.getInt(_cursorIndexOfSystolic);
              final int _tmpDiastolic;
              _tmpDiastolic = _cursor.getInt(_cursorIndexOfDiastolic);
              _tmpReading = new BloodPressureReading(_tmpSystolic,_tmpDiastolic);
            }  else  {
              _tmpReading = null;
            }
            _result = new BloodPressureMeasurement(_tmpUuid,_tmpReading,_tmpSyncStatus,_tmpUserUuid,_tmpFacilityUuid,_tmpPatientUuid,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpRecordedAt);
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
  public BloodPressureMeasurement bloodPressureImmediate(final UUID uuid) {
    final String _sql = "SELECT * FROM bloodpressuremeasurement WHERE uuid = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __uuidRoomTypeConverter.fromUuid(uuid);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
      final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
      final int _cursorIndexOfUserUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "userUuid");
      final int _cursorIndexOfFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "facilityUuid");
      final int _cursorIndexOfPatientUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "patientUuid");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
      final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deletedAt");
      final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
      final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
      final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
      final BloodPressureMeasurement _result;
      if(_cursor.moveToFirst()) {
        final UUID _tmpUuid;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfUuid);
        _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp_1);
        final SyncStatus _tmpSyncStatus;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfSyncStatus);
        _tmpSyncStatus = __roomTypeConverter.toEnum(_tmp_2);
        final UUID _tmpUserUuid;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfUserUuid);
        _tmpUserUuid = __uuidRoomTypeConverter.toUuid(_tmp_3);
        final UUID _tmpFacilityUuid;
        final String _tmp_4;
        _tmp_4 = _cursor.getString(_cursorIndexOfFacilityUuid);
        _tmpFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_4);
        final UUID _tmpPatientUuid;
        final String _tmp_5;
        _tmp_5 = _cursor.getString(_cursorIndexOfPatientUuid);
        _tmpPatientUuid = __uuidRoomTypeConverter.toUuid(_tmp_5);
        final Instant _tmpCreatedAt;
        final String _tmp_6;
        _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
        _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_6);
        final Instant _tmpUpdatedAt;
        final String _tmp_7;
        _tmp_7 = _cursor.getString(_cursorIndexOfUpdatedAt);
        _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_7);
        final Instant _tmpDeletedAt;
        final String _tmp_8;
        _tmp_8 = _cursor.getString(_cursorIndexOfDeletedAt);
        _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_8);
        final Instant _tmpRecordedAt;
        final String _tmp_9;
        _tmp_9 = _cursor.getString(_cursorIndexOfRecordedAt);
        _tmpRecordedAt = __instantRoomTypeConverter.toInstant(_tmp_9);
        final BloodPressureReading _tmpReading;
        if (! (_cursor.isNull(_cursorIndexOfSystolic) && _cursor.isNull(_cursorIndexOfDiastolic))) {
          final int _tmpSystolic;
          _tmpSystolic = _cursor.getInt(_cursorIndexOfSystolic);
          final int _tmpDiastolic;
          _tmpDiastolic = _cursor.getInt(_cursorIndexOfDiastolic);
          _tmpReading = new BloodPressureReading(_tmpSystolic,_tmpDiastolic);
        }  else  {
          _tmpReading = null;
        }
        _result = new BloodPressureMeasurement(_tmpUuid,_tmpReading,_tmpSyncStatus,_tmpUserUuid,_tmpFacilityUuid,_tmpPatientUuid,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpRecordedAt);
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
  public Flowable<Integer> count() {
    final String _sql = "SELECT COUNT(uuid) FROM bloodpressuremeasurement";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[]{"bloodpressuremeasurement"}, new Callable<Integer>() {
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
    final String _sql = "SELECT COUNT(uuid) FROM BloodPressureMeasurement WHERE syncStatus = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __roomTypeConverter.fromEnum(syncStatus);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return RxRoom.createFlowable(__db, false, new String[]{"BloodPressureMeasurement"}, new Callable<Integer>() {
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
  public int recordedBloodPressureCountForPatientImmediate(final UUID patientUuid) {
    final String _sql = "\n"
            + "      SELECT COUNT(uuid)\n"
            + "      FROM bloodpressuremeasurement\n"
            + "      WHERE patientUuid = ? AND deletedAt IS NULL\n"
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
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Observable<Integer> recordedBloodPressureCountForPatient(final UUID patientUuid) {
    final String _sql = "\n"
            + "      SELECT COUNT(uuid)\n"
            + "      FROM bloodpressuremeasurement\n"
            + "      WHERE patientUuid = ? AND deletedAt IS NULL\n"
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
    return RxRoom.createObservable(__db, false, new String[]{"bloodpressuremeasurement"}, new Callable<Integer>() {
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
  public Flowable<List<BloodPressureMeasurement>> newestMeasurementsForPatient(
      final UUID patientUuid, final int limit) {
    final String _sql = "\n"
            + "      SELECT * FROM bloodpressuremeasurement\n"
            + "        WHERE patientUuid = ? AND deletedAt IS NULL\n"
            + "        ORDER BY recordedAt DESC LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __uuidRoomTypeConverter.fromUuid(patientUuid);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    return RxRoom.createFlowable(__db, false, new String[]{"bloodpressuremeasurement"}, new Callable<List<BloodPressureMeasurement>>() {
      @Override
      public List<BloodPressureMeasurement> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
          final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
          final int _cursorIndexOfUserUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "userUuid");
          final int _cursorIndexOfFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "facilityUuid");
          final int _cursorIndexOfPatientUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "patientUuid");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deletedAt");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
          final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
          final List<BloodPressureMeasurement> _result = new ArrayList<BloodPressureMeasurement>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final BloodPressureMeasurement _item;
            final UUID _tmpUuid;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfUuid);
            _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp_1);
            final SyncStatus _tmpSyncStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfSyncStatus);
            _tmpSyncStatus = __roomTypeConverter.toEnum(_tmp_2);
            final UUID _tmpUserUuid;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfUserUuid);
            _tmpUserUuid = __uuidRoomTypeConverter.toUuid(_tmp_3);
            final UUID _tmpFacilityUuid;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfFacilityUuid);
            _tmpFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_4);
            final UUID _tmpPatientUuid;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfPatientUuid);
            _tmpPatientUuid = __uuidRoomTypeConverter.toUuid(_tmp_5);
            final Instant _tmpCreatedAt;
            final String _tmp_6;
            _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
            _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_6);
            final Instant _tmpUpdatedAt;
            final String _tmp_7;
            _tmp_7 = _cursor.getString(_cursorIndexOfUpdatedAt);
            _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_7);
            final Instant _tmpDeletedAt;
            final String _tmp_8;
            _tmp_8 = _cursor.getString(_cursorIndexOfDeletedAt);
            _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_8);
            final Instant _tmpRecordedAt;
            final String _tmp_9;
            _tmp_9 = _cursor.getString(_cursorIndexOfRecordedAt);
            _tmpRecordedAt = __instantRoomTypeConverter.toInstant(_tmp_9);
            final BloodPressureReading _tmpReading;
            if (! (_cursor.isNull(_cursorIndexOfSystolic) && _cursor.isNull(_cursorIndexOfDiastolic))) {
              final int _tmpSystolic;
              _tmpSystolic = _cursor.getInt(_cursorIndexOfSystolic);
              final int _tmpDiastolic;
              _tmpDiastolic = _cursor.getInt(_cursorIndexOfDiastolic);
              _tmpReading = new BloodPressureReading(_tmpSystolic,_tmpDiastolic);
            }  else  {
              _tmpReading = null;
            }
            _item = new BloodPressureMeasurement(_tmpUuid,_tmpReading,_tmpSyncStatus,_tmpUserUuid,_tmpFacilityUuid,_tmpPatientUuid,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpRecordedAt);
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
  public List<BloodPressureMeasurement> newestMeasurementsForPatientImmediate(
      final UUID patientUuid, final int limit) {
    final String _sql = "\n"
            + "      SELECT * FROM bloodpressuremeasurement\n"
            + "        WHERE patientUuid = ? AND deletedAt IS NULL\n"
            + "        ORDER BY recordedAt DESC LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __uuidRoomTypeConverter.fromUuid(patientUuid);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
      final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
      final int _cursorIndexOfUserUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "userUuid");
      final int _cursorIndexOfFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "facilityUuid");
      final int _cursorIndexOfPatientUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "patientUuid");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
      final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deletedAt");
      final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
      final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
      final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
      final List<BloodPressureMeasurement> _result = new ArrayList<BloodPressureMeasurement>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final BloodPressureMeasurement _item;
        final UUID _tmpUuid;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfUuid);
        _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp_1);
        final SyncStatus _tmpSyncStatus;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfSyncStatus);
        _tmpSyncStatus = __roomTypeConverter.toEnum(_tmp_2);
        final UUID _tmpUserUuid;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfUserUuid);
        _tmpUserUuid = __uuidRoomTypeConverter.toUuid(_tmp_3);
        final UUID _tmpFacilityUuid;
        final String _tmp_4;
        _tmp_4 = _cursor.getString(_cursorIndexOfFacilityUuid);
        _tmpFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_4);
        final UUID _tmpPatientUuid;
        final String _tmp_5;
        _tmp_5 = _cursor.getString(_cursorIndexOfPatientUuid);
        _tmpPatientUuid = __uuidRoomTypeConverter.toUuid(_tmp_5);
        final Instant _tmpCreatedAt;
        final String _tmp_6;
        _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
        _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_6);
        final Instant _tmpUpdatedAt;
        final String _tmp_7;
        _tmp_7 = _cursor.getString(_cursorIndexOfUpdatedAt);
        _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_7);
        final Instant _tmpDeletedAt;
        final String _tmp_8;
        _tmp_8 = _cursor.getString(_cursorIndexOfDeletedAt);
        _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_8);
        final Instant _tmpRecordedAt;
        final String _tmp_9;
        _tmp_9 = _cursor.getString(_cursorIndexOfRecordedAt);
        _tmpRecordedAt = __instantRoomTypeConverter.toInstant(_tmp_9);
        final BloodPressureReading _tmpReading;
        if (! (_cursor.isNull(_cursorIndexOfSystolic) && _cursor.isNull(_cursorIndexOfDiastolic))) {
          final int _tmpSystolic;
          _tmpSystolic = _cursor.getInt(_cursorIndexOfSystolic);
          final int _tmpDiastolic;
          _tmpDiastolic = _cursor.getInt(_cursorIndexOfDiastolic);
          _tmpReading = new BloodPressureReading(_tmpSystolic,_tmpDiastolic);
        }  else  {
          _tmpReading = null;
        }
        _item = new BloodPressureMeasurement(_tmpUuid,_tmpReading,_tmpSyncStatus,_tmpUserUuid,_tmpFacilityUuid,_tmpPatientUuid,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpRecordedAt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<PatientToFacilityId> patientToFacilityIds(final List<UUID> patientUuids) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("\n");
    _stringBuilder.append("      SELECT patientUuid, facilityUuid");
    _stringBuilder.append("\n");
    _stringBuilder.append("      FROM bloodpressuremeasurement");
    _stringBuilder.append("\n");
    _stringBuilder.append("      WHERE patientUuid IN (");
    final int _inputSize = patientUuids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND deletedAt IS NULL");
    _stringBuilder.append("\n");
    _stringBuilder.append("      ");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (UUID _item : patientUuids) {
      final String _tmp;
      _tmp = __uuidRoomTypeConverter.fromUuid(_item);
      if (_tmp == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindString(_argIndex, _tmp);
      }
      _argIndex ++;
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPatientUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "patientUuid");
      final int _cursorIndexOfFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "facilityUuid");
      final List<PatientToFacilityId> _result = new ArrayList<PatientToFacilityId>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final PatientToFacilityId _item_1;
        final UUID _tmpPatientUuid;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfPatientUuid);
        _tmpPatientUuid = __uuidRoomTypeConverter.toUuid(_tmp_1);
        final UUID _tmpFacilityUuid;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfFacilityUuid);
        _tmpFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_2);
        _item_1 = new PatientToFacilityId(_tmpPatientUuid,_tmpFacilityUuid);
        _result.add(_item_1);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public boolean haveBpsForPatientChangedSince(final UUID patientUuid,
      final Instant instantToCompare, final SyncStatus pendingStatus) {
    final String _sql = "\n"
            + "        SELECT (\n"
            + "            CASE\n"
            + "                WHEN (COUNT(uuid) > 0) THEN 1\n"
            + "                ELSE 0\n"
            + "            END\n"
            + "        )\n"
            + "        FROM BloodPressureMeasurement\n"
            + "        WHERE updatedAt > ? AND syncStatus = ? AND patientUuid = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    final String _tmp;
    _tmp = __instantRoomTypeConverter.fromInstant(instantToCompare);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    _argIndex = 2;
    final String _tmp_1;
    _tmp_1 = __roomTypeConverter.fromEnum(pendingStatus);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_1);
    }
    _argIndex = 3;
    final String _tmp_2;
    _tmp_2 = __uuidRoomTypeConverter.fromUuid(patientUuid);
    if (_tmp_2 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_2);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final boolean _result;
      if(_cursor.moveToFirst()) {
        final int _tmp_3;
        _tmp_3 = _cursor.getInt(0);
        _result = _tmp_3 != 0;
      } else {
        _result = false;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Observable<List<BloodPressureMeasurement>> allBloodPressures(final UUID patientUuid) {
    final String _sql = "\n"
            + "      SELECT * FROM bloodpressuremeasurement\n"
            + "      WHERE patientUuid == ? AND deletedAt IS NULL\n"
            + "      ORDER BY recordedAt DESC\n"
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
    return RxRoom.createObservable(__db, false, new String[]{"bloodpressuremeasurement"}, new Callable<List<BloodPressureMeasurement>>() {
      @Override
      public List<BloodPressureMeasurement> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
          final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
          final int _cursorIndexOfUserUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "userUuid");
          final int _cursorIndexOfFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "facilityUuid");
          final int _cursorIndexOfPatientUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "patientUuid");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deletedAt");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
          final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
          final List<BloodPressureMeasurement> _result = new ArrayList<BloodPressureMeasurement>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final BloodPressureMeasurement _item;
            final UUID _tmpUuid;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfUuid);
            _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp_1);
            final SyncStatus _tmpSyncStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfSyncStatus);
            _tmpSyncStatus = __roomTypeConverter.toEnum(_tmp_2);
            final UUID _tmpUserUuid;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfUserUuid);
            _tmpUserUuid = __uuidRoomTypeConverter.toUuid(_tmp_3);
            final UUID _tmpFacilityUuid;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfFacilityUuid);
            _tmpFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_4);
            final UUID _tmpPatientUuid;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfPatientUuid);
            _tmpPatientUuid = __uuidRoomTypeConverter.toUuid(_tmp_5);
            final Instant _tmpCreatedAt;
            final String _tmp_6;
            _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
            _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_6);
            final Instant _tmpUpdatedAt;
            final String _tmp_7;
            _tmp_7 = _cursor.getString(_cursorIndexOfUpdatedAt);
            _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_7);
            final Instant _tmpDeletedAt;
            final String _tmp_8;
            _tmp_8 = _cursor.getString(_cursorIndexOfDeletedAt);
            _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_8);
            final Instant _tmpRecordedAt;
            final String _tmp_9;
            _tmp_9 = _cursor.getString(_cursorIndexOfRecordedAt);
            _tmpRecordedAt = __instantRoomTypeConverter.toInstant(_tmp_9);
            final BloodPressureReading _tmpReading;
            if (! (_cursor.isNull(_cursorIndexOfSystolic) && _cursor.isNull(_cursorIndexOfDiastolic))) {
              final int _tmpSystolic;
              _tmpSystolic = _cursor.getInt(_cursorIndexOfSystolic);
              final int _tmpDiastolic;
              _tmpDiastolic = _cursor.getInt(_cursorIndexOfDiastolic);
              _tmpReading = new BloodPressureReading(_tmpSystolic,_tmpDiastolic);
            }  else  {
              _tmpReading = null;
            }
            _item = new BloodPressureMeasurement(_tmpUuid,_tmpReading,_tmpSyncStatus,_tmpUserUuid,_tmpFacilityUuid,_tmpPatientUuid,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpRecordedAt);
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
  public DataSource.Factory<Integer, BloodPressureMeasurement> allBloodPressuresDataSource(
      final UUID patientUuid) {
    final String _sql = "\n"
            + "      SELECT * FROM bloodpressuremeasurement\n"
            + "      WHERE patientUuid == ? AND deletedAt IS NULL\n"
            + "      ORDER BY recordedAt DESC\n"
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
    return new DataSource.Factory<Integer, BloodPressureMeasurement>() {
      @Override
      public LimitOffsetDataSource<BloodPressureMeasurement> create() {
        return new LimitOffsetDataSource<BloodPressureMeasurement>(__db, _statement, false , "bloodpressuremeasurement") {
          @Override
          protected List<BloodPressureMeasurement> convertRows(Cursor cursor) {
            final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(cursor, "uuid");
            final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(cursor, "syncStatus");
            final int _cursorIndexOfUserUuid = CursorUtil.getColumnIndexOrThrow(cursor, "userUuid");
            final int _cursorIndexOfFacilityUuid = CursorUtil.getColumnIndexOrThrow(cursor, "facilityUuid");
            final int _cursorIndexOfPatientUuid = CursorUtil.getColumnIndexOrThrow(cursor, "patientUuid");
            final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(cursor, "createdAt");
            final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(cursor, "updatedAt");
            final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(cursor, "deletedAt");
            final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(cursor, "recordedAt");
            final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(cursor, "systolic");
            final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(cursor, "diastolic");
            final List<BloodPressureMeasurement> _res = new ArrayList<BloodPressureMeasurement>(cursor.getCount());
            while(cursor.moveToNext()) {
              final BloodPressureMeasurement _item;
              final UUID _tmpUuid;
              final String _tmp_1;
              _tmp_1 = cursor.getString(_cursorIndexOfUuid);
              _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp_1);
              final SyncStatus _tmpSyncStatus;
              final String _tmp_2;
              _tmp_2 = cursor.getString(_cursorIndexOfSyncStatus);
              _tmpSyncStatus = __roomTypeConverter.toEnum(_tmp_2);
              final UUID _tmpUserUuid;
              final String _tmp_3;
              _tmp_3 = cursor.getString(_cursorIndexOfUserUuid);
              _tmpUserUuid = __uuidRoomTypeConverter.toUuid(_tmp_3);
              final UUID _tmpFacilityUuid;
              final String _tmp_4;
              _tmp_4 = cursor.getString(_cursorIndexOfFacilityUuid);
              _tmpFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_4);
              final UUID _tmpPatientUuid;
              final String _tmp_5;
              _tmp_5 = cursor.getString(_cursorIndexOfPatientUuid);
              _tmpPatientUuid = __uuidRoomTypeConverter.toUuid(_tmp_5);
              final Instant _tmpCreatedAt;
              final String _tmp_6;
              _tmp_6 = cursor.getString(_cursorIndexOfCreatedAt);
              _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_6);
              final Instant _tmpUpdatedAt;
              final String _tmp_7;
              _tmp_7 = cursor.getString(_cursorIndexOfUpdatedAt);
              _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_7);
              final Instant _tmpDeletedAt;
              final String _tmp_8;
              _tmp_8 = cursor.getString(_cursorIndexOfDeletedAt);
              _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_8);
              final Instant _tmpRecordedAt;
              final String _tmp_9;
              _tmp_9 = cursor.getString(_cursorIndexOfRecordedAt);
              _tmpRecordedAt = __instantRoomTypeConverter.toInstant(_tmp_9);
              final BloodPressureReading _tmpReading;
              if (! (cursor.isNull(_cursorIndexOfSystolic) && cursor.isNull(_cursorIndexOfDiastolic))) {
                final int _tmpSystolic;
                _tmpSystolic = cursor.getInt(_cursorIndexOfSystolic);
                final int _tmpDiastolic;
                _tmpDiastolic = cursor.getInt(_cursorIndexOfDiastolic);
                _tmpReading = new BloodPressureReading(_tmpSystolic,_tmpDiastolic);
              }  else  {
                _tmpReading = null;
              }
              _item = new BloodPressureMeasurement(_tmpUuid,_tmpReading,_tmpSyncStatus,_tmpUserUuid,_tmpFacilityUuid,_tmpPatientUuid,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpRecordedAt);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  @Override
  public List<BloodPressureMeasurement> getAllBloodPressureMeasurements() {
    final String _sql = "SELECT * FROM BloodPressureMeasurement";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
      final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
      final int _cursorIndexOfUserUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "userUuid");
      final int _cursorIndexOfFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "facilityUuid");
      final int _cursorIndexOfPatientUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "patientUuid");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
      final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deletedAt");
      final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
      final int _cursorIndexOfSystolic = CursorUtil.getColumnIndexOrThrow(_cursor, "systolic");
      final int _cursorIndexOfDiastolic = CursorUtil.getColumnIndexOrThrow(_cursor, "diastolic");
      final List<BloodPressureMeasurement> _result = new ArrayList<BloodPressureMeasurement>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final BloodPressureMeasurement _item;
        final UUID _tmpUuid;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfUuid);
        _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp);
        final SyncStatus _tmpSyncStatus;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfSyncStatus);
        _tmpSyncStatus = __roomTypeConverter.toEnum(_tmp_1);
        final UUID _tmpUserUuid;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfUserUuid);
        _tmpUserUuid = __uuidRoomTypeConverter.toUuid(_tmp_2);
        final UUID _tmpFacilityUuid;
        final String _tmp_3;
        _tmp_3 = _cursor.getString(_cursorIndexOfFacilityUuid);
        _tmpFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_3);
        final UUID _tmpPatientUuid;
        final String _tmp_4;
        _tmp_4 = _cursor.getString(_cursorIndexOfPatientUuid);
        _tmpPatientUuid = __uuidRoomTypeConverter.toUuid(_tmp_4);
        final Instant _tmpCreatedAt;
        final String _tmp_5;
        _tmp_5 = _cursor.getString(_cursorIndexOfCreatedAt);
        _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_5);
        final Instant _tmpUpdatedAt;
        final String _tmp_6;
        _tmp_6 = _cursor.getString(_cursorIndexOfUpdatedAt);
        _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_6);
        final Instant _tmpDeletedAt;
        final String _tmp_7;
        _tmp_7 = _cursor.getString(_cursorIndexOfDeletedAt);
        _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_7);
        final Instant _tmpRecordedAt;
        final String _tmp_8;
        _tmp_8 = _cursor.getString(_cursorIndexOfRecordedAt);
        _tmpRecordedAt = __instantRoomTypeConverter.toInstant(_tmp_8);
        final BloodPressureReading _tmpReading;
        if (! (_cursor.isNull(_cursorIndexOfSystolic) && _cursor.isNull(_cursorIndexOfDiastolic))) {
          final int _tmpSystolic;
          _tmpSystolic = _cursor.getInt(_cursorIndexOfSystolic);
          final int _tmpDiastolic;
          _tmpDiastolic = _cursor.getInt(_cursorIndexOfDiastolic);
          _tmpReading = new BloodPressureReading(_tmpSystolic,_tmpDiastolic);
        }  else  {
          _tmpReading = null;
        }
        _item = new BloodPressureMeasurement(_tmpUuid,_tmpReading,_tmpSyncStatus,_tmpUserUuid,_tmpFacilityUuid,_tmpPatientUuid,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpRecordedAt);
        _result.add(_item);
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
    _stringBuilder.append("UPDATE bloodpressuremeasurement SET syncStatus = ");
    _stringBuilder.append("?");
    _stringBuilder.append(" WHERE uuid IN (");
    final int _inputSize = uuids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
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
