public class Main {

    public static final int N = 5;

    public static class Employee {

//        ФИО
        private String name;
        private String secondName;
//        должность
        private String post;
//        email
        private String email;
//        телефон
        private String phoneNumber;
//        зарплата
        private int salary;
//        возраст
        private int age;
//        Вакцинирован
        private boolean chip;

        public Employee() {}

        public Employee(String name, String secondName, String post, String email, String phoneNumber, int salary, int age, boolean chip) {
            this.name = name;
            this.secondName = secondName;
            this.post = post;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.salary = salary;
            this.age = age;
            this.chip = chip;
        }

        public void  getAllFormat() {
            System.out.println("\n" + secondName + " "+ name + "(" + age + " лет)->" +
                    "\n\tПочта: " + email +
                    "\n\tНомер телефона: " + phoneNumber +
                    "\n\tЗарплата: " + salary + " бубликов" +
                    "\n\tЧипирован: " + ((chip)? "Да" : "Нет"));
        }

        public boolean checkChipping() {
            return this.chip;
        }

    }

    public static void main(String[] args) {

        Employee[] employees = new Employee[N];

        employees[0] = new Employee(
                "Иван",
                "Иванов",
                "Самый главный",
                "ivan@mail.ru",
                "89994442233",
                1000000,
                18,
                true
        );
        employees[1] = new Employee(
                "Петр",
                "Петров",
                "Почти самый главный",
                "pepe@mail.ru",
                "89871236677",
                30000,
                50,
                true
        );
        employees[2] = new Employee(
                "Сергей",
                "Серов",
                "Самый не главный",
                "sergo@mail.ru",
                "88888888888",
                45000,
                32,
                false
        );
        employees[3] = new Employee(
                "Ксения",
                "Ксеньева",
                "Простой офисный обитатель",
                "cs_1_6@mail.ru",
                "88856565",
                75000,
                21,
                false
        );
        employees[4] = new Employee(
                "Николай",
                "Лобачевский",
                "Главный среди неглавных",
                "god_of_geometry@mail.ru",
                "88005553555",
                50000,
                27,
                false
        );

        System.out.println("Успешно чипированы: ");
        for (int i = 0; i < N; i++) {
            if (employees[i].checkChipping())
                employees[i].getAllFormat();
        }

    }

}
