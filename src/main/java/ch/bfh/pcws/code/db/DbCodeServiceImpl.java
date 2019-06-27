package ch.bfh.pcws.code.db;

import ch.bfh.pcws.code.CodeService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbCodeServiceImpl implements CodeService {

    private static final String GET_BARCODE_NUMBER_CALL = "SELECT get_barcode_number(?)";
    private static final int ACCOUNT_ID_PARAMETER_INDEX = 1;
    private  static final int BARCODE_NUMBER_RESULT_INDEX = 1;

    private final PreparedStatement preparedStatement;

    public DbCodeServiceImpl(DataSource dataSource) {
        try {
            Connection connection = dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement(GET_BARCODE_NUMBER_CALL);
        } catch (SQLException e) {
            throw new DbCodeServiceException("Cannot prepare statement", e);
        }
    }

    @Override
    public int getBarcodeNumber(int accountId) {
        try {
            preparedStatement.setInt(ACCOUNT_ID_PARAMETER_INDEX, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(BARCODE_NUMBER_RESULT_INDEX);
        } catch (SQLException e) {
            throw new DbCodeServiceException("Cannot get barcode number", e);
        }
    }
}
