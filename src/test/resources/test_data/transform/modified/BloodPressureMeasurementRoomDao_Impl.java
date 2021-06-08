package test_data;

import android.database.Cursor;
import androidx.paging.DataSource;
import androidx.paging.DataSource.Factory;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import org.simple.clinic.patient.SyncStatus;
import org.simple.clinic.util.room.InstantRoomTypeConverter;
import org.simple.clinic.util.room.UuidRoomTypeConverter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                if (_tmpReading != null) {
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
                return new LimitOffsetDataSource<BloodPressureMeasurement>(__db, _statement, false, "bloodpressuremeasurement") {
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
                        while (cursor.moveToNext()) {
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
                            if (!(cursor.isNull(_cursorIndexOfSystolic) && cursor.isNull(_cursorIndexOfDiastolic))) {
                                final int _tmpSystolic;
                                _tmpSystolic = cursor.getInt(_cursorIndexOfSystolic);
                                final int _tmpDiastolic;
                                _tmpDiastolic = cursor.getInt(_cursorIndexOfDiastolic);
                                _tmpReading = new BloodPressureReading(_tmpSystolic, _tmpDiastolic);
                            } else {
                                _tmpReading = null;
                            }
                            _item = new BloodPressureMeasurement(_tmpUuid, _tmpReading, _tmpSyncStatus, _tmpUserUuid, _tmpFacilityUuid, _tmpPatientUuid, _tmpCreatedAt, _tmpUpdatedAt, _tmpDeletedAt, _tmpRecordedAt);
                            _res.add(_item);
                        }
                        return _res;
                    }
                };
            }
        };
    }

    private static <T> T measureAndReport(final String methodName, final kotlin.jvm.functions.Function0<T> block) {
        final long start = System.currentTimeMillis();
        final T result;
        result = block.invoke();
        final java.time.Duration timeTaken = java.time.Duration.ofMillis(System.currentTimeMillis() - start);
        org.simple.clinic.SqlPerformanceReporter.report("BloodPressureMeasurementRoomDao_Impl", methodName, timeTaken);
        return result;
    }
}
