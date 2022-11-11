/**
 * @author AbednegoSteven - 1972009
 */
public class Fighter {
    private String name;
    private int health;
    private Statistic statistic;

    public Fighter(){

    }

    public Fighter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public Statistic getStatistic(){
        return statistic;
    }
    public void setStatistic(Statistic statistic){
        this.statistic = statistic;
    }
    public void attack(Fighter fighter){
        System.out.println(this.getName() + " attacking " + fighter.getName());
        fighter.setHealth(fighter.getHealth() - 10);
        System.out.println(fighter.getName()+ " healths reduced to "+fighter.getHealth());
    }
    public void specialAttack(Fighter fighter){
        System.out.println(this.getName() + " use special attack to "+fighter.getName());
        fighter.setHealth(fighter.getHealth() - 20);
        System.out.println(fighter.getName() + " healths reduced to "+fighter.getHealth());
    }
    public void defend(Fighter fighter){
        System.out.println(this.getName()+ " use max defend");
        fighter.setHealth(fighter.getHealth() - 5);
        System.out.println(fighter.getName() + " healths reduced to "+fighter.getHealth());
    }
}
