# Lolskinshards Backend


A **small proof of concept** to display, filter, and search **League of Legends skin shards** more efficiently.  
The official client UI is too minimal once you have 100+ shards and want to decide which to **buy** or **reroll**.

This app uses the **local League Client API** that the LoL client itself communicates with.

> Backend currently retrieves credentials **only on macOS**.

- On startup the backend fetches **all skin shards** from the local League Client API.
- For every shard it downloads the **champion/skin images from Riot’s Data Dragon (ddragon)** and stores them **locally** to speed up the UI.

### Endpoints

1. `GET /skins`
    - Returns all loaded skin shards (with local image paths if available)

2. `PATCH /{id}/wanted`
    - Body contains boolean value
    - Used only to mark the user’s decision, no fancy logic yet

---


## Notes

- **No code style**
- **No separation of concerns**
- **No tests**
- **Not production-ready**

---

## Reference

Based on info from  
[reganchan.ca – Efficient Hextech Crafting](https://reganchan.ca/blog/efficient-hextech-crafting)
