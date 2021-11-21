
public class BushCell extends CommonPlace implements isSpecial{

	@Override
	public void addSpecialAbility(Hero hero) {
		// TODO Auto-generated method stub
		hero.setDexterity(hero.getDexterity()*1.1);
		hero.setAttributeChanged(HeroSkill.DEXTERITY.getHeroSkillName());
	}
}
