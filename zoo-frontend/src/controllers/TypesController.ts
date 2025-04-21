import axios from 'axios';

interface SpeciesResponse {
    species: string[];
}

interface FoodTypesResponse {
    foodTypes: string[];
}

/**
 * Получить список типов пищи
 * @returns {Promise<string[]>}
 */
export async function fetchFoodTypes(): Promise<string[]> {
    const response = await axios.get<FoodTypesResponse>('/api/dictionaries/food-types');
    // теперь response.data — { foodTypes: string[] }
    return response.data.foodTypes;
}

/**
 * Получить список видов животных
 * @returns {Promise<string[]>}
 */
export async function fetchAnimalSpecies(): Promise<string[]> {
    const response = await axios.get<SpeciesResponse>('/api/dictionaries/animal-species');
    // теперь response.data — { species: string[] }
    return response.data.species;
}
