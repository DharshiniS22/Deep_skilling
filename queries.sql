
CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE
);
 
CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
 
CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);
 
CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER,
    InterestRate NUMBER,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
 
CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
);
ALTER TABLE Customers ADD IsVIP CHAR(1) DEFAULT 'N';
 
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);
 
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (3, 'Robert King', TO_DATE('1955-02-10', 'YYYY-MM-DD'), 15000, SYSDATE);
 
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (1, 1, 'Savings', 1000, SYSDATE);
 
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (2, 2, 'Checking', 1500, SYSDATE);
 
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (3, 3, 'Savings', 8000, SYSDATE);
 
INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (1, 1, SYSDATE, 200, 'Deposit');
 
INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (2, 2, SYSDATE, 300, 'Withdrawal');
 
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (2, 3, 10000, 6, SYSDATE, ADD_MONTHS(SYSDATE, 24));
 
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (3, 2, 3000, 4, ADD_MONTHS(SYSDATE, -12), SYSDATE + 15);
 
INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));
 
INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));
 
COMMIT;
DECLARE
    v_age NUMBER;
BEGIN
    FOR cust_rec IN (SELECT CustomerID, Name, DOB FROM Customers) LOOP
 
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, cust_rec.DOB) / 12);
 
        IF v_age > 60 THEN
            UPDATE Loans
               SET InterestRate = InterestRate - (InterestRate * 0.01)
             WHERE CustomerID = cust_rec.CustomerID;
 
            DBMS_OUTPUT.PUT_LINE('Applied 1% interest discount for: '
                                  || cust_rec.Name || ' (Age: ' || v_age || ')');
        END IF;
 
    END LOOP;
 
    COMMIT;
END;
/
BEGIN
  FOR cust_rec IN (SELECT CustomerID, Name, Balance FROM Customers) LOOP

      IF cust_rec.Balance > 10000 THEN
          UPDATE Customers
             SET IsVIP = 'Y'
           WHERE CustomerID = cust_rec.CustomerID;

          DBMS_OUTPUT.PUT_LINE(cust_rec.Name || ' promoted to VIP status.');
      ELSE
          UPDATE Customers
             SET IsVIP = 'N'
           WHERE CustomerID = cust_rec.CustomerID;
      END IF;

  END LOOP;

  COMMIT;
END;
/
DECLARE
    CURSOR due_loans_cur IS
        SELECT l.LoanID, l.EndDate, l.LoanAmount, c.Name
          FROM Loans l
          JOIN Customers c ON c.CustomerID = l.CustomerID
         WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    FOR loan_rec IN due_loans_cur LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || loan_rec.Name ||
                              ', your loan (ID: ' || loan_rec.LoanID ||
                              ', Amount: ' || loan_rec.LoanAmount ||
                              ') is due on ' ||
                              TO_CHAR(loan_rec.EndDate, 'DD-MON-YYYY') || '.');
    END LOOP;
END;
/
 
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
IS
BEGIN
    FOR acc_rec IN (SELECT AccountID, Balance
                       FROM Accounts
                      WHERE AccountType = 'Savings') LOOP
 
        UPDATE Accounts
           SET Balance = Balance + (Balance * 0.01),
               LastModified = SYSDATE
         WHERE AccountID = acc_rec.AccountID;
 
        DBMS_OUTPUT.PUT_LINE('Interest applied to Account ' ||
                              acc_rec.AccountID || '. Old Balance: ' ||
                              acc_rec.Balance || ', New Balance: ' ||
                              (acc_rec.Balance + (acc_rec.Balance * 0.01)));
    END LOOP;
 
    COMMIT;
END ProcessMonthlyInterest;
/
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department  IN VARCHAR2,
    p_bonus_pct   IN NUMBER
)
IS
    v_count NUMBER := 0;
BEGIN
    FOR emp_rec IN (SELECT EmployeeID, Name, Salary
                       FROM Employees
                      WHERE Department = p_department) LOOP
 
        UPDATE Employees
           SET Salary = Salary + (Salary * p_bonus_pct / 100)
         WHERE EmployeeID = emp_rec.EmployeeID;
 
        v_count := v_count + 1;
 
        DBMS_OUTPUT.PUT_LINE('Bonus applied to ' || emp_rec.Name ||
                              '. Old Salary: ' || emp_rec.Salary ||
                              ', New Salary: ' ||
                              (emp_rec.Salary + (emp_rec.Salary * p_bonus_pct / 100)));
    END LOOP;
 
    IF v_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    END IF;
 
    COMMIT;
END UpdateEmployeeBonus;
/
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account  IN NUMBER,
    p_to_account    IN NUMBER,
    p_amount        IN NUMBER
)
IS
    v_from_balance   Accounts.Balance%TYPE;
    v_to_exists      NUMBER;
    v_new_txn_id     NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_to_exists FROM Accounts WHERE AccountID = p_to_account;
    IF v_to_exists = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Transfer failed: Destination account ' ||
                              p_to_account || ' does not exist.');
        RETURN;
    END IF;

    SELECT Balance INTO v_from_balance
      FROM Accounts
     WHERE AccountID = p_from_account
     FOR UPDATE;

    IF v_from_balance < p_amount THEN
        DBMS_OUTPUT.PUT_LINE('Transfer failed: Insufficient balance in Account ' ||
                              p_from_account || '. Available: ' || v_from_balance ||
                              ', Requested: ' || p_amount);
        RETURN;
    END IF;

    UPDATE Accounts
       SET Balance = Balance - p_amount,
           LastModified = SYSDATE
     WHERE AccountID = p_from_account;

    UPDATE Accounts
       SET Balance = Balance + p_amount,
           LastModified = SYSDATE
     WHERE AccountID = p_to_account;

    SELECT NVL(MAX(TransactionID), 0) + 1 INTO v_new_txn_id FROM Transactions;
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (v_new_txn_id, p_from_account, SYSDATE, p_amount, 'Withdrawal');

    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (v_new_txn_id + 1, p_to_account, SYSDATE, p_amount, 'Deposit');

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Transfer successful: ' || p_amount ||
                          ' moved from Account ' || p_from_account ||
                          ' to Account ' || p_to_account || '.');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Transfer failed: Source account ' ||
                              p_from_account || ' does not exist.');
        ROLLBACK;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Transfer failed due to an unexpected error: ' || SQLERRM);
        ROLLBACK;
END TransferFunds;
/
EXEC ProcessMonthlyInterest;
EXEC UpdateEmployeeBonus('IT', 10);
EXEC TransferFunds(1, 2, 200);
