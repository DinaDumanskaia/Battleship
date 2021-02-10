class Test {

    public static void main(String[] args) {

        Programmer teamLead = new TeamLead(1);
        Programmer programmer = new Programmer(1);
        programmer.employ();
        teamLead.employ();
    }

    public static class TeamLead extends Programmer {

        public TeamLead(int numProgrammer) {
            super(numProgrammer);
        }

        protected void employ() {
            System.out.println(numProgrammer + " teamlead");
        }

    }

    public static class Programmer {

        protected int numProgrammer;

        public Programmer(int numProgrammer) {
            this.numProgrammer = numProgrammer;
        }

        protected void employ() {
            System.out.println(numProgrammer + " programmer");
        }
    }
}