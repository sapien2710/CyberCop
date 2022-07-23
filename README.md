# JavaFX_CyberCop
A GUI-based JavaFX application to create, read, update, delete FTC files in CSV and TSV formats

The Federal Trade Commission (FTC) is a government agency that is responsible for "protecting consumers and competition by preventing anticompetitive, deceptive, and unfair business practices through law enforcement, advocacy, and education without unduly burdening legitimate business activity." It conducts investigative cases against companies that engage in unlawful practices that harm consumers in any way. 

This tool can help analyze these FTC cases. Cases from the dataset are in a tab-separated file named CyberCop-CSVData.csv and CyberCop-TSVData.tsv

Important things to note in data:

1. TSV Data set: A sample of this data set is shown in table below. It has following parameters:
- Case Date
- Case Number
- Case Title
- Case Type (i.e., Federal, Administrative)
- Case Link
- Case Category
- Case Notes
However, not all rows may have data in these columns.

![image](https://user-images.githubusercontent.com/53651395/180624604-6d92ea76-3390-46cc-8e80-c161f98bf099.png)

2. CSV Data set: This data has same columns as above, but since some of the values, such as Title or Case notes can have commas within them, they are enclosed within double-quotes as shown in table below.

![image](https://user-images.githubusercontent.com/53651395/180624647-1dea6cad-ce4c-4975-a37a-43b773da391f.png)


This application features a complex system architecture to incorporate the flexibilities provided through the basic four pillars of this object-oriented programming language : 

![image](https://user-images.githubusercontent.com/53651395/180625289-c5156763-891e-442e-91af-5893b94533a2.png)

![image](https://user-images.githubusercontent.com/53651395/180625306-3d93614a-7c8b-4158-a872-0aaece8b23ed.png)



The GUI-based application has following functionalities :

- Read and view all cases from csv and tsv files in an organized tabular format

![image](https://user-images.githubusercontent.com/53651395/180625061-eb8d2060-bc38-4dde-91f3-c904509cbb4d.png)

- Search cases based on multiple filters : Case number, title, type and year
- Add/Modify/Delete case from GUI and save it to the original csv and tsv files

![image](https://user-images.githubusercontent.com/53651395/180625076-c37c9d49-fccb-4b56-9a6c-f8c4476e326b.png)

- Display FTC case count graphically through a chart

![image](https://user-images.githubusercontent.com/53651395/180625095-00d1b17e-fa04-4054-8b6e-9545b5d786d9.png)

- Error Handling (runtime exceptions) to handle invalid case addition, modification or deletion

![image](https://user-images.githubusercontent.com/53651395/180625108-4b70ff5c-da4c-4d18-9daa-e2af3b6fb9c5.png)

