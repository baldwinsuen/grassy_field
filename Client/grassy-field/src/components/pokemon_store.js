import { store as createStore } from "react-easy-state";

const getByUrl = items => items.url

export const store = createStore({
    pokemon_data: [],
    landing_pokemon: [],
    team_pokemon: []
});

export const getAllPokemon = async () => {
  const urls = await fetch('https://pokeapi.co/api/v2/pokemon?limit=151&offset=0').then(async response => {
    const data = await response.json()
    const results = data.results
    return results
  })
  store.pokemon_data = await Promise.all(
    urls.map(items => getByUrl(items))
    .map(url => fetch(url).then(r => r.json()))
  )
};

export const getRandomPokemon = async () => {
  var random = Math.floor(Math.random() * (151 - 1 + 1) + 1)
  const pokemon = await fetch('https://pokeapi.co/api/v2/pokemon/' + random).then(async response => {
    const data = await response.json()
    return data
  })
  store.landing_pokemon = pokemon
}


