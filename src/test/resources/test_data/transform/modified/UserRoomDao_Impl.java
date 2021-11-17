package org.simple.rmg;

import android.database.Cursor;
import androidx.room.EmptyResultSetException;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.lang.Boolean;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.simple.clinic.facility.Facility;
import org.simple.clinic.facility.FacilityConfig;
import org.simple.clinic.location.Coordinates;
import org.simple.clinic.patient.SyncStatus;
import org.simple.clinic.util.room.InstantRoomTypeConverter;
import org.simple.clinic.util.room.UuidRoomTypeConverter;

@SuppressWarnings({ "unchecked", "deprecation" })
public final class UserRoomDao_Impl extends User.RoomDao {

  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final UuidRoomTypeConverter __uuidRoomTypeConverter = new UuidRoomTypeConverter();

  private final UserStatus.RoomTypeConverter __roomTypeConverter = new UserStatus.RoomTypeConverter();

  private final InstantRoomTypeConverter __instantRoomTypeConverter = new InstantRoomTypeConverter();

  private final User.LoggedInStatus.RoomTypeConverter __roomTypeConverter_1 = new User.LoggedInStatus.RoomTypeConverter();

  private final User.CapabilityStatus.RoomTypeConverter __roomTypeConverter_2 = new User.CapabilityStatus.RoomTypeConverter();

  private final EntityDeletionOrUpdateAdapter<User> __deletionAdapterOfUser;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLoggedInStatusForUser;

  private final SharedSQLiteStatement __preparedStmtOfSetCurrentFacility;

  private final SyncStatus.RoomTypeConverter __roomTypeConverter_3 = new SyncStatus.RoomTypeConverter();

  public UserRoomDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {

      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `LoggedInUser` (`uuid`,`fullName`,`phoneNumber`,`pinDigest`,`status`,`createdAt`,`updatedAt`,`loggedInStatus`,`registrationFacilityUuid`,`currentFacilityUuid`,`teleconsultPhoneNumber`,`capability_canTeleconsult`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        final String _tmp;
        _tmp = __uuidRoomTypeConverter.fromUuid(value.getUuid());
        if (_tmp == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, _tmp);
        }
        if (value.getFullName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFullName());
        }
        if (value.getPhoneNumber() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPhoneNumber());
        }
        if (value.getPinDigest() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPinDigest());
        }
        final String _tmp_1;
        _tmp_1 = __roomTypeConverter.fromEnum(value.getStatus());
        if (_tmp_1 == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, _tmp_1);
        }
        final String _tmp_2;
        _tmp_2 = __instantRoomTypeConverter.fromInstant(value.getCreatedAt());
        if (_tmp_2 == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, _tmp_2);
        }
        final String _tmp_3;
        _tmp_3 = __instantRoomTypeConverter.fromInstant(value.getUpdatedAt());
        if (_tmp_3 == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, _tmp_3);
        }
        final String _tmp_4;
        _tmp_4 = __roomTypeConverter_1.fromEnum(value.getLoggedInStatus());
        if (_tmp_4 == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, _tmp_4);
        }
        final String _tmp_5;
        _tmp_5 = __uuidRoomTypeConverter.fromUuid(value.getRegistrationFacilityUuid());
        if (_tmp_5 == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, _tmp_5);
        }
        final String _tmp_6;
        _tmp_6 = __uuidRoomTypeConverter.fromUuid(value.getCurrentFacilityUuid());
        if (_tmp_6 == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, _tmp_6);
        }
        if (value.getTeleconsultPhoneNumber() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getTeleconsultPhoneNumber());
        }
        final User.Capabilities _tmpCapabilities = value.getCapabilities();
        if (_tmpCapabilities != null) {
          final String _tmp_7;
          _tmp_7 = __roomTypeConverter_2.fromEnum(_tmpCapabilities.getCanTeleconsult());
          if (_tmp_7 == null) {
            stmt.bindNull(12);
          } else {
            stmt.bindString(12, _tmp_7);
          }
        } else {
          stmt.bindNull(12);
        }
      }
    };
    this.__deletionAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {

      @Override
      public String createQuery() {
        return "DELETE FROM `LoggedInUser` WHERE `uuid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        final String _tmp;
        _tmp = __uuidRoomTypeConverter.fromUuid(value.getUuid());
        if (_tmp == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, _tmp);
        }
      }
    };
    this.__preparedStmtOfUpdateLoggedInStatusForUser = new SharedSQLiteStatement(__db) {

      @Override
      public String createQuery() {
        final String _query = "UPDATE LoggedInUser SET loggedInStatus = ? WHERE uuid = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetCurrentFacility = new SharedSQLiteStatement(__db) {

      @Override
      public String createQuery() {
        final String _query = "UPDATE LoggedInUser SET currentFacilityUuid = ?";
        return _query;
      }
    };
  }

  @Override
  public void deleteUser(final User user) {
    measureAndReport("deleteUser", () -> {
      __db.assertNotSuspendingTransaction();
      __db.beginTransaction();
      try {
        __deletionAdapterOfUser.handle(user);
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
      return null;
    });
  }

  @Override
  public void updateLoggedInStatusForUser(final UUID userUuId, final User.LoggedInStatus loggedInStatus) {
    measureAndReport("updateLoggedInStatusForUser", () -> {
      __db.assertNotSuspendingTransaction();
      final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLoggedInStatusForUser.acquire();
      int _argIndex = 1;
      final String _tmp;
      _tmp = __roomTypeConverter_1.fromEnum(loggedInStatus);
      if (_tmp == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _tmp);
      }
      _argIndex = 2;
      final String _tmp_1;
      _tmp_1 = __uuidRoomTypeConverter.fromUuid(userUuId);
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
        __preparedStmtOfUpdateLoggedInStatusForUser.release(_stmt);
      }
      return null;
    });
  }

  @Override
  public int setCurrentFacility(final UUID facilityUuid) {
    return measureAndReport("setCurrentFacility", () -> {
      __db.assertNotSuspendingTransaction();
      final SupportSQLiteStatement _stmt = __preparedStmtOfSetCurrentFacility.acquire();
      int _argIndex = 1;
      final String _tmp;
      _tmp = __uuidRoomTypeConverter.fromUuid(facilityUuid);
      if (_tmp == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _tmp);
      }
      __db.beginTransaction();
      try {
        final int _result = _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        __db.endTransaction();
        __preparedStmtOfSetCurrentFacility.release(_stmt);
      }
    });
  }

  @Override
  public Flowable<List<User>> user() {
    final String _sql = "SELECT * FROM LoggedInUser LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[] { "LoggedInUser" }, new Callable<List<User>>() {

      @Override
      public List<User> call() throws Exception {
        return measureAndReport("user", () -> {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
            final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
            final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumber");
            final int _cursorIndexOfPinDigest = CursorUtil.getColumnIndexOrThrow(_cursor, "pinDigest");
            final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
            final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
            final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
            final int _cursorIndexOfLoggedInStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "loggedInStatus");
            final int _cursorIndexOfRegistrationFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "registrationFacilityUuid");
            final int _cursorIndexOfCurrentFacilityUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "currentFacilityUuid");
            final int _cursorIndexOfTeleconsultPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "teleconsultPhoneNumber");
            final int _cursorIndexOfCanTeleconsult = CursorUtil.getColumnIndexOrThrow(_cursor, "capability_canTeleconsult");
            final List<User> _result = new ArrayList<User>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final User _item;
              final UUID _tmpUuid;
              final String _tmp;
              _tmp = _cursor.getString(_cursorIndexOfUuid);
              _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp);
              final String _tmpFullName;
              _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
              final String _tmpPhoneNumber;
              _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
              final String _tmpPinDigest;
              _tmpPinDigest = _cursor.getString(_cursorIndexOfPinDigest);
              final UserStatus _tmpStatus;
              final String _tmp_1;
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
              _tmpStatus = __roomTypeConverter.toEnum(_tmp_1);
              final Instant _tmpCreatedAt;
              final String _tmp_2;
              _tmp_2 = _cursor.getString(_cursorIndexOfCreatedAt);
              _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_2);
              final Instant _tmpUpdatedAt;
              final String _tmp_3;
              _tmp_3 = _cursor.getString(_cursorIndexOfUpdatedAt);
              _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_3);
              final User.LoggedInStatus _tmpLoggedInStatus;
              final String _tmp_4;
              _tmp_4 = _cursor.getString(_cursorIndexOfLoggedInStatus);
              _tmpLoggedInStatus = __roomTypeConverter_1.toEnum(_tmp_4);
              final UUID _tmpRegistrationFacilityUuid;
              final String _tmp_5;
              _tmp_5 = _cursor.getString(_cursorIndexOfRegistrationFacilityUuid);
              _tmpRegistrationFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_5);
              final UUID _tmpCurrentFacilityUuid;
              final String _tmp_6;
              _tmp_6 = _cursor.getString(_cursorIndexOfCurrentFacilityUuid);
              _tmpCurrentFacilityUuid = __uuidRoomTypeConverter.toUuid(_tmp_6);
              final String _tmpTeleconsultPhoneNumber;
              _tmpTeleconsultPhoneNumber = _cursor.getString(_cursorIndexOfTeleconsultPhoneNumber);
              final User.Capabilities _tmpCapabilities;
              if (!(_cursor.isNull(_cursorIndexOfCanTeleconsult))) {
                final User.CapabilityStatus _tmpCanTeleconsult;
                final String _tmp_7;
                _tmp_7 = _cursor.getString(_cursorIndexOfCanTeleconsult);
                _tmpCanTeleconsult = __roomTypeConverter_2.toEnum(_tmp_7);
                _tmpCapabilities = new User.Capabilities(_tmpCanTeleconsult);
              } else {
                _tmpCapabilities = null;
              }
              _item = new User(_tmpUuid, _tmpFullName, _tmpPhoneNumber, _tmpPinDigest, _tmpStatus, _tmpCreatedAt, _tmpUpdatedAt, _tmpLoggedInStatus, _tmpRegistrationFacilityUuid, _tmpCurrentFacilityUuid, _tmpTeleconsultPhoneNumber, _tmpCapabilities);
              _result.add(_item);
            }
            return _result;
          } finally {
            _cursor.close();
          }
        });
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Single<Integer> userCount() {
    final String _sql = "SELECT COUNT(uuid) FROM LoggedInUser";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createSingle(new Callable<Integer>() {

      @Override
      public Integer call() throws Exception {
        return measureAndReport("userCount", () -> {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final Integer _result;
            if (_cursor.moveToFirst()) {
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
            if (_result == null) {
              throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
            }
            return _result;
          } finally {
            _cursor.close();
          }
        });
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flowable<Facility> currentFacility() {
    final String _sql = "\n" + "        SELECT \n" + "         F.uuid, F.name, F.facilityType,\n" + "         F.streetAddress, F.villageOrColony, F.district,\n" + "         F.state, F.country, F.pinCode,\n" + "         F.protocolUuid, F.groupUuid,\n" + "         F.location_latitude, F.location_longitude,\n" + "         F.createdAt, F.updatedAt, F.deletedAt,\n" + "         F.syncStatus,\n" + "         F.config_diabetesManagementEnabled,\n" + "         F.config_teleconsultationEnabled,\n" + "         F.syncGroup\n" + "        FROM Facility F\n" + "        INNER JOIN LoggedInUser ON LoggedInUser.currentFacilityUuid = F.uuid\n" + "        LIMIT 1\n" + "      ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[] { "Facility", "LoggedInUser" }, new Callable<Facility>() {

      @Override
      public Facility call() throws Exception {
        return measureAndReport("currentFacility", () -> {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfFacilityType = CursorUtil.getColumnIndexOrThrow(_cursor, "facilityType");
            final int _cursorIndexOfStreetAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "streetAddress");
            final int _cursorIndexOfVillageOrColony = CursorUtil.getColumnIndexOrThrow(_cursor, "villageOrColony");
            final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "district");
            final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
            final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
            final int _cursorIndexOfPinCode = CursorUtil.getColumnIndexOrThrow(_cursor, "pinCode");
            final int _cursorIndexOfProtocolUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "protocolUuid");
            final int _cursorIndexOfGroupUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "groupUuid");
            final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "location_latitude");
            final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "location_longitude");
            final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
            final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
            final int _cursorIndexOfDeletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deletedAt");
            final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "syncStatus");
            final int _cursorIndexOfDiabetesManagementEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "config_diabetesManagementEnabled");
            final int _cursorIndexOfTeleconsultationEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "config_teleconsultationEnabled");
            final int _cursorIndexOfSyncGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "syncGroup");
            final Facility _result;
            if (_cursor.moveToFirst()) {
              final UUID _tmpUuid;
              final String _tmp;
              _tmp = _cursor.getString(_cursorIndexOfUuid);
              _tmpUuid = __uuidRoomTypeConverter.toUuid(_tmp);
              final String _tmpName;
              _tmpName = _cursor.getString(_cursorIndexOfName);
              final String _tmpFacilityType;
              _tmpFacilityType = _cursor.getString(_cursorIndexOfFacilityType);
              final String _tmpStreetAddress;
              _tmpStreetAddress = _cursor.getString(_cursorIndexOfStreetAddress);
              final String _tmpVillageOrColony;
              _tmpVillageOrColony = _cursor.getString(_cursorIndexOfVillageOrColony);
              final String _tmpDistrict;
              _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
              final String _tmpState;
              _tmpState = _cursor.getString(_cursorIndexOfState);
              final String _tmpCountry;
              _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
              final String _tmpPinCode;
              _tmpPinCode = _cursor.getString(_cursorIndexOfPinCode);
              final UUID _tmpProtocolUuid;
              final String _tmp_1;
              _tmp_1 = _cursor.getString(_cursorIndexOfProtocolUuid);
              _tmpProtocolUuid = __uuidRoomTypeConverter.toUuid(_tmp_1);
              final UUID _tmpGroupUuid;
              final String _tmp_2;
              _tmp_2 = _cursor.getString(_cursorIndexOfGroupUuid);
              _tmpGroupUuid = __uuidRoomTypeConverter.toUuid(_tmp_2);
              final Instant _tmpCreatedAt;
              final String _tmp_3;
              _tmp_3 = _cursor.getString(_cursorIndexOfCreatedAt);
              _tmpCreatedAt = __instantRoomTypeConverter.toInstant(_tmp_3);
              final Instant _tmpUpdatedAt;
              final String _tmp_4;
              _tmp_4 = _cursor.getString(_cursorIndexOfUpdatedAt);
              _tmpUpdatedAt = __instantRoomTypeConverter.toInstant(_tmp_4);
              final Instant _tmpDeletedAt;
              final String _tmp_5;
              _tmp_5 = _cursor.getString(_cursorIndexOfDeletedAt);
              _tmpDeletedAt = __instantRoomTypeConverter.toInstant(_tmp_5);
              final SyncStatus _tmpSyncStatus;
              final String _tmp_6;
              _tmp_6 = _cursor.getString(_cursorIndexOfSyncStatus);
              _tmpSyncStatus = __roomTypeConverter_3.toEnum(_tmp_6);
              final String _tmpSyncGroup;
              _tmpSyncGroup = _cursor.getString(_cursorIndexOfSyncGroup);
              final Coordinates _tmpLocation;
              if (!(_cursor.isNull(_cursorIndexOfLatitude) && _cursor.isNull(_cursorIndexOfLongitude))) {
                final double _tmpLatitude;
                _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
                final double _tmpLongitude;
                _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
                _tmpLocation = new Coordinates(_tmpLatitude, _tmpLongitude);
              } else {
                _tmpLocation = null;
              }
              final FacilityConfig _tmpConfig;
              if (!(_cursor.isNull(_cursorIndexOfDiabetesManagementEnabled) && _cursor.isNull(_cursorIndexOfTeleconsultationEnabled))) {
                final boolean _tmpDiabetesManagementEnabled;
                final int _tmp_7;
                _tmp_7 = _cursor.getInt(_cursorIndexOfDiabetesManagementEnabled);
                _tmpDiabetesManagementEnabled = _tmp_7 != 0;
                final Boolean _tmpTeleconsultationEnabled;
                final Integer _tmp_8;
                if (_cursor.isNull(_cursorIndexOfTeleconsultationEnabled)) {
                  _tmp_8 = null;
                } else {
                  _tmp_8 = _cursor.getInt(_cursorIndexOfTeleconsultationEnabled);
                }
                _tmpTeleconsultationEnabled = _tmp_8 == null ? null : _tmp_8 != 0;
                _tmpConfig = new FacilityConfig(_tmpDiabetesManagementEnabled, _tmpTeleconsultationEnabled);
              } else {
                _tmpConfig = null;
              }
              _result = new Facility(_tmpUuid, _tmpName, _tmpFacilityType, _tmpStreetAddress, _tmpVillageOrColony, _tmpDistrict, _tmpState, _tmpCountry, _tmpPinCode, _tmpProtocolUuid, _tmpGroupUuid, _tmpLocation, _tmpCreatedAt, _tmpUpdatedAt, _tmpSyncStatus, _tmpDeletedAt, _tmpConfig, _tmpSyncGroup);
            } else {
              _result = null;
            }
            return _result;
          } finally {
            _cursor.close();
          }
        });
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public UUID currentFacilityUuid() {
    return measureAndReport("currentFacilityUuid", () -> {
      final String _sql = "SELECT currentFacilityUuid FROM LoggedInUser LIMIT 1";
      final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
      __db.assertNotSuspendingTransaction();
      final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
      try {
        final UUID _result;
        if (_cursor.moveToFirst()) {
          final String _tmp;
          _tmp = _cursor.getString(0);
          _result = __uuidRoomTypeConverter.toUuid(_tmp);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _cursor.close();
        _statement.release();
      }
    });
  }

  @Override
  public UserFacilityDetails userAndFacilityDetails() {
    return measureAndReport("userAndFacilityDetails", () -> {
      final String _sql = "\n" + "      SELECT U.uuid userId, F.uuid currentFacilityId, F.syncGroup currentSyncGroupId\n" + "      FROM LoggedInUser U\n" + "      INNER JOIN Facility F ON U.currentFacilityUuid == F.uuid\n" + "    ";
      final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
      __db.assertNotSuspendingTransaction();
      final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
      try {
        final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
        final int _cursorIndexOfCurrentFacilityId = CursorUtil.getColumnIndexOrThrow(_cursor, "currentFacilityId");
        final int _cursorIndexOfCurrentSyncGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "currentSyncGroupId");
        final UserFacilityDetails _result;
        if (_cursor.moveToFirst()) {
          final UUID _tmpUserId;
          final String _tmp;
          _tmp = _cursor.getString(_cursorIndexOfUserId);
          _tmpUserId = __uuidRoomTypeConverter.toUuid(_tmp);
          final UUID _tmpCurrentFacilityId;
          final String _tmp_1;
          _tmp_1 = _cursor.getString(_cursorIndexOfCurrentFacilityId);
          _tmpCurrentFacilityId = __uuidRoomTypeConverter.toUuid(_tmp_1);
          final String _tmpCurrentSyncGroupId;
          _tmpCurrentSyncGroupId = _cursor.getString(_cursorIndexOfCurrentSyncGroupId);
          _result = new UserFacilityDetails(_tmpUserId, _tmpCurrentFacilityId, _tmpCurrentSyncGroupId);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _cursor.close();
        _statement.release();
      }
    });
  }

  private static <T> T measureAndReport(final String methodName, final kotlin.jvm.functions.Function0<T> block) {
    final long start = System.currentTimeMillis();
    final T result;
    org.simple.clinic.SqlPerformanceReporter.begin("UserRoomDao_Impl", start, methodName);
    result = block.invoke();
    org.simple.clinic.SqlPerformanceReporter.end("UserRoomDao_Impl", start, methodName);
    return result;
  }
}
