//child class to CommonPlace class and where heroes and monsters can move and fight. Heroes get extra Agility

public class CaveCell extends CommonPlace implements isSpecial{

	@Override
	public void addSpecialAbility(Hero hero) {
		// TODO Auto-generated method stub
		hero.setAgility(hero.getAgility()*1.1);
		hero.setAttributeChanged(HeroSkill.AGILITY.getHeroSkillName());
		
	}

}
