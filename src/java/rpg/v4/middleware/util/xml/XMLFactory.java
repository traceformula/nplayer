package rpg.v4.middleware.util.xml;

import rpg.v4.middleware.constants.CategoryEnum;
import rpg.v4.middleware.jaxb.*;

/**
 * Creates new modifiers.
 */
public class XMLFactory
{
    private static final ObjectFactory factory = new ObjectFactory();

    public static V4Modifier createModifier(boolean includeDice)
    {
        V4Modifier modifier = factory.createV4Modifier();
        modifier.setModifierClass("Integer");

        if (includeDice)
        {
            V4Modifier.DiceModifier diceModifier = factory.createV4ModifierDiceModifier();
            diceModifier.setDiceType(6);
            diceModifier.setNumberOfDice(1);
            diceModifier.setOverallMultiplier(1);
            modifier.setDiceModifier(diceModifier);
        }

        return modifier;
    }

    public static V4Race createRace()
    {
        V4Race race = factory.createV4Race();
        V4ModifierList modList = factory.createV4ModifierList();
        race.setV4ModifierList(modList);

        // Setup race with default modifiers
        V4Modifier abilityModifier = factory.createV4Modifier();
        abilityModifier.setModifier(2);
        abilityModifier.setTargetID("Strength");
        abilityModifier.setSourceID("Racial");
        abilityModifier.setV4ModifierType("Racial");
        modList.getV4Modifier().add(abilityModifier);

        V4Modifier abilityModifier_2 = factory.createV4Modifier();
        abilityModifier_2.setModifier(2);
        abilityModifier_2.setTargetID("Charisma");
        abilityModifier_2.setSourceID("Racial");
        abilityModifier_2.setV4ModifierType("Racial");
        modList.getV4Modifier().add(abilityModifier_2);

        V4Modifier speedModifier = factory.createV4Modifier();
        speedModifier.setModifier(6);
        speedModifier.setTargetID("Speed");
        speedModifier.setSourceID("Racial");
        speedModifier.setV4ModifierType("Racial");
        modList.getV4Modifier().add(speedModifier);

        race.getV4BodyPart().addAll(XMLRaceKit.instance().getStandardBodyPartList());
        return race;
    }

    public static V4Class createClass()
    {
        V4Class characterClass = factory.createV4Class();
        V4ModifierList modList = factory.createV4ModifierList();
        characterClass.setV4ModifierList(modList);

        // Setup class with default modifiers
        V4Modifier hpAtFirstLvl = factory.createV4Modifier();
        hpAtFirstLvl.setModifier(12);
        hpAtFirstLvl.setTargetID("Hit points at 1st lvl");
        hpAtFirstLvl.setSourceID("Character class");
        hpAtFirstLvl.setV4ModifierType("Untyped");
        modList.getV4Modifier().add(hpAtFirstLvl);

        V4Modifier hpPrConsecutiveLvl = factory.createV4Modifier();
        hpPrConsecutiveLvl.setModifier(5);
        hpPrConsecutiveLvl.setTargetID("Hit points pr gained lvl");
        hpPrConsecutiveLvl.setSourceID("Character class");
        hpPrConsecutiveLvl.setV4ModifierType("Untyped");
        modList.getV4Modifier().add(hpPrConsecutiveLvl);

        V4Modifier bonusToDefence = factory.createV4Modifier();
        bonusToDefence.setModifier(2);
        bonusToDefence.setTargetID("Will");
        bonusToDefence.setSourceID("Character class");
        bonusToDefence.setV4ModifierType("Untyped");
        modList.getV4Modifier().add(bonusToDefence);

        V4Modifier numOfHealingSurges = factory.createV4Modifier();
        numOfHealingSurges.setModifier(2);
        numOfHealingSurges.setTargetID("# of Healing surges");
        numOfHealingSurges.setSourceID("Character class");
        numOfHealingSurges.setV4ModifierType("Untyped");
        modList.getV4Modifier().add(numOfHealingSurges);

        V4Modifier armorProficiency = factory.createV4Modifier();
        armorProficiency.setModifierClass("String");
        armorProficiency.setModifier("Cloth Armor");
        armorProficiency.setTargetID("Added Armor prof.");
        armorProficiency.setSourceID("Racial");
        armorProficiency.setV4ModifierType("Racial");
        modList.getV4Modifier().add(armorProficiency);

        V4Modifier weaponProficiency = factory.createV4Modifier();
        weaponProficiency.setModifierClass("String");
        weaponProficiency.setModifier("Simple Melee");
        weaponProficiency.setTargetID("Added Weapon prof.");
        weaponProficiency.setSourceID("Racial");
        weaponProficiency.setV4ModifierType("Racial");
        modList.getV4Modifier().add(weaponProficiency);

        return characterClass;
    }

    public static V4Weapon createWeapon()
    {
        V4Weapon weapon = factory.createV4Weapon();
        V4Weapon.Damage damage = factory.createV4WeaponDamage();
        V4Weapon.Properties properties = factory.createV4WeaponProperties();
        weapon.setDamage(damage);
        weapon.setProperties(properties);

        V4ModifierList modList = factory.createV4ModifierList();
        weapon.setV4ModifierList(modList);
        return weapon;
    }

    public static V4ItemCapsule createItemCapsule(V4Weapon weapon)
    {
        V4ItemCapsule itemCapsule = factory.createV4ItemCapsule();
        itemCapsule.setName(weapon.getName());
        itemCapsule.setWeight(weapon.getWeight());
        itemCapsule.setV4Weapon(weapon);

        return itemCapsule;
    }

    public static V4ItemCapsule createItemCapsule(V4Item item)
    {
        V4ItemCapsule itemCapsule = factory.createV4ItemCapsule();
        itemCapsule.setName(item.getName());
        itemCapsule.setWeight(item.getWeight());
        itemCapsule.setV4Item(item);

        return itemCapsule;
    }

    public static V4Entity createEntity()
    {
        V4Entity v4Entity = factory.createV4Entity();
        V4Entity.V4Inventory inventory = factory.createV4EntityV4Inventory();
        v4Entity.setV4Inventory(inventory);

        v4Entity.setV4EquippedObjects(factory.createV4EntityV4EquippedObjects());
        return v4Entity;
    }

    public static V4State createV4State()
    {
        V4State v4State = factory.createV4State();
        return v4State;
    }

    // Some items can have their own states. This creates the dynamic v4state.
    public static V4State createV4State(V4ItemCapsule itemCapsule)
    {
        V4State v4State = createV4State();
        v4State.setDisplayName(itemCapsule.getUUID());
        v4State.setStateID(itemCapsule.getUUID());
        v4State.setType("Integer");
        v4State.setSubType("Untyped");
        v4State.setCategory(CategoryEnum.DYNAMIC_BABS.toString());
        v4State.setInitialValue("0");
        v4State.getV4StateID().add("Base Attack Bonus");

        return v4State;
    }

    public static V4EntityNameKeyPair createV4EntityNameKeyPair(String name, String key)
    {
        if (null == name || "".equals(name))
        {
            throw new IllegalArgumentException("Name cannot be null or empty string.");
        }

        if (null == key || "".equals(key))
        {
            throw new IllegalArgumentException("Key cannot be null or empty string.");
        }
        
        V4EntityNameKeyPair entityCapsule = factory.createV4EntityNameKeyPair();
        entityCapsule.setName(name);
        entityCapsule.setKey(key);
        return entityCapsule;
    }

    public static V4Group createV4Group()
    {
        V4Group v4Group = factory.createV4Group();
        v4Group.setName("New group");
        V4Group.Color color = factory.createV4GroupColor();
        color.setRed(0);
        color.setGreen(51);
        color.setBlue(255);
        v4Group.setColor(color);
        return v4Group;
    }

    public static V4Item createItem()
    {
        V4Item v4Item = factory.createV4Item();
        V4ModifierList modList = factory.createV4ModifierList();
        v4Item.setV4ModifierList(modList);
        return v4Item;
    }

    public static V4GenericAction createGenericAction()
    {
        V4GenericAction v4GenAction = factory.createV4GenericAction();
        V4GenericAction.Attack v4GAAttack = factory.createV4GenericActionAttack();
        V4GenericAction.HitModifiers v4GAHitMods = factory.createV4GenericActionHitModifiers();
        V4GenericAction.MissModifiers v4GAMissMods = factory.createV4GenericActionMissModifiers();
        V4GenericAction.Requirements v4GARequirements = factory.createV4GenericActionRequirements();

        v4GenAction.setAttack(v4GAAttack);
        v4GenAction.setHitModifiers(v4GAHitMods);
        v4GenAction.setMissModifiers(v4GAMissMods);
        v4GenAction.setRequirements(v4GARequirements);

        return v4GenAction;
    }
}
