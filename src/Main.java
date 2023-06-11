import java.util.Random;

public class Main {
    public static int bossHealth = 800;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static String[] heroesClass = {"Physic", "Magical", "Mental", "Medical"}; //Добавление 4го класса
    public static int[] heroesHealth = {320, 300, 280, 400};
    public static int[] heroesDamage = {25, 20, 15, 0};
    public static int roundNumber = 0;
    public static int heal = 56 ;  //ДЗ: Обявление и инициализация переменной навыка "Лечение"

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }

    public static void chooseDefence(){
        Random random= new Random();
        int randomIndex=random.nextInt((heroesClass.length)-1);
        bossDefence=heroesClass[randomIndex];
    }
    public static void playRound() {
        roundNumber++;
        chooseDefence();
        bossHits();
        heroesHits();
        medicHealth();
        printStatistics();
    }

    public static void medicHealth() {// ДЗ: Навык лечение и его условия выполнения
        if(heroesHealth[3]>0){          // Условие невозмножности лечения медика
            if (heroesHealth[0] > 0 && heroesHealth[0] <= 100) {
                heroesHealth[0] = heroesHealth[0] + heal;
                System.out.println("Physic was healed");
            } else if (heroesHealth[1] > 0 && heroesHealth[1] <= 100) {
                heroesHealth[1] = heroesHealth[1] + heal;
                System.out.println("Magic was healed");
            } else if (heroesHealth[2] > 0 && heroesHealth[2] <= 100) {
                heroesHealth[2] = heroesHealth[2] + heal;
                System.out.println("Mental was healed");
            }
        }
    }

    public static void bossHits() {// УРОН БОССА
        for (int i = 0; i < heroesHealth.length; i++) {
            if(heroesHealth[i]>0){
                if(heroesHealth[i]-bossDamage<0){
                    heroesHealth[i]=0;
                }else{
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHits() { //УРОН ГЕРОЕВ
        for (int i = 0; i < heroesDamage.length; i++) {
            if(bossHealth>0 && heroesHealth[i]>0) {
                int damage = heroesDamage[i];
                if(heroesClass[i]==bossDefence){
                    Random random=new Random();
                    int coeff=random.nextInt(4)+2;
                    damage = heroesDamage[i]*coeff;
                    System.out.println("Critical damage: "+damage);
                }
                if(bossHealth-damage<0){bossHealth=0;
                }else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes Won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 & heroesHealth[3] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroDead = false;
                break;
            }
        }
        if (allHeroDead) {
            System.out.println("Boss won");
        }
        return allHeroDead;
    }

    public static void printStatistics() {

        System.out.println("round" + roundNumber + "__________");
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: " + (bossDefence == null ? "No Defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesClass[i] + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
    }
}