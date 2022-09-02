package io.catalyte.training.sportsproducts.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * An error class to handle data access exceptions
 */
public class DataAccessError extends DataAccessException {

  /**
   * Constructor for a DataAccessError
   * @param msg - An error message
   */
  public DataAccessError ( String msg ) {
    super(msg);
  }
}