-- Create the database and connect to it.
connect 'jdbc:derby://localhost:1527/dictionaryDB;create=true';

-- Add a user to the database, username dev, password dev
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.user.dev','dev');

-- Grant all privileges to user dev
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.fullAccessUsers','dev');

-- Disconnect from the newly created database
disconnect;

-- Reconnect to the newly created database as user dev
connect 'jdbc:derby://localhost:1527/dictionaryDB;user=dev;password=dev';

-- Create tables
CREATE TABLE WORDS (Original_word VARCHAR(20) PRIMARY KEY, Language_Word VARCHAR(2), Date_of_create DATE, Study BOOLEAN, Date_of_study DATE  );
CREATE TABLE WORDS_AND_TRANSLATEDWORDS (WORD_Original_word VARCHAR(20) REFERENCES WORDS, TRANSLATEDWORDS_Original_word VARCHAR(20) REFERENCES WORDS);
-- Populate tables

INSERT INTO WORDS (Original_word, Language_Word, Date_of_create) VALUES ('good', 'EN', '2016-12-13');


