# Personal Finance Tracker

## Project Overview

As a squad, our mission is to collaboratively design and implement a **Personal Finance Tracker**—a console-based application focused on tracking income and expenses. The focus is on:

- Proper use of **Object-Oriented Programming (OOP)** concepts  
- Adherence to **SOLID** design principles  
- Consistent and clean use of **Git Flow** throughout development  

## Screenshots
<table style="width: 100%; border-collapse: collapse;"><tbody><tr><th style="width: 50%; text-align: center; border: 1px solid #ccc; padding: 8px;">add transaction </th><th style="width: 50%; text-align: center; border: 1px solid #ccc; padding: 8px;">month report</th></tr><tr><td style="width: 50%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="add transaction" src="https://github.com/user-attachments/assets/5ae19966-b8fd-4f59-bef7-c8908fd927a2"></td><td style="width: 50%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="month report" src="https://github.com/user-attachments/assets/6bc5e6b9-858a-478d-aa7a-d4010f7ce403"></td></tr></tbody></table>

## Project structure 
- **entity/**: Contains models like `Transaction`, `Category`, and any Enum's.
- **transaction/**: Manages all transaction-related operations In Memory, Persistent.  
- **monthreport/**: Handles reporting logic based on transaction dates.  
- **util/**: Includes file handling, date formatting, and other helpers.  
- **test/**: Contains test/check functions for validating business logic.
  
## main Features
- Add new transactions (income or expense)  
- View a list of all transactions  
- Edit existing transactions  
- Delete transactions  
- Assign categories to transactions (e.g., Food, Rent, Salary)  
- Generate monthly summaries and balance reports, based on transaction dates  

## Additional Features

- **Persistent Storage**: Transactions can be saved to a text file using serialization, allowing users to retain their data across sessions.  
- **Automated Testing**: A custom GitHub Action automatically runs internal check functions on each pull request. Test results appear directly on the pull request page, helping maintain code quality and consistency.  
