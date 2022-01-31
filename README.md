# MinecraftCustomContent
API for adding custom content to minecraft

## Feautures

- [x] Custom Items API
- [ ] Custom Blocks API
- [ ] Custom HUD API

## Custom Items
```java
public class TestLightningStick extends CustomItem implements IInteractItemEvent {
    public TestLightningStick(JavaPlugin plugin) {
        super(new NamespacedKey(plugin, "test_lightning_stick"));
    }

    @Override
    public ItemStack create(int amount) {
        return _create(amount, Material.STICK, "Lightning rod", "strikes lightning bolt on click");
    }

    @Override
    public void onInteractEvent(PlayerInteractEvent event) {
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        Location loc = event.getClickedBlock().getRelative(event.getBlockFace()).getLocation();
        loc.getWorld().spawnEntity(loc, EntityType.LIGHTNING);
    }
}
```
Then register in onEnable with:
```java
new TestLightningStick(this);
```