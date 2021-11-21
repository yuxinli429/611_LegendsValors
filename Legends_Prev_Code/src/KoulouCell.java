
public class KoulouCell extends CommonPlace implements isSpecial{

	@Override
	public void addSpecialAbility(Hero hero) {
		// TODO Auto-generated method stub
		hero.setStrength(hero.getStrength()*1.1);
		hero.setAttributeChanged(HeroSkill.STRENGTH.getHeroSkillName());		
	}

}
