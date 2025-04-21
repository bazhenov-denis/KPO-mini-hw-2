import axios from 'axios'

// Интерфейс запроса — он должен совпадать с тем, что ждёт бэкенд
export interface AnimalRequest {
    name: string;
    species: string;       // код вида, например "LION"
    birthDate: string;     // "yyyy-MM-dd"
    gender: 'MALE' | 'FEMALE';
    favoriteFood: string;  // код еды, например "MEAT"
    status: 'HEALTHY' | 'SICK';
}

// Интерфейс ответа — совпадает с вашей доменной моделью
export interface AnimalResponse {
    id: number;
    name: string;
    species: string;
    birthDate: string;
    gender: 'MALE' | 'FEMALE';
    favoriteFood: string;
    status: 'HEALTHY' | 'SICK';
    // … и прочие поля, если есть
}

/**
 * Создать новое животное
 */
export async function createAnimal(req: AnimalRequest): Promise<AnimalResponse> {
    const res = await axios.post<AnimalResponse>('api/animals', req);
    return res.data;
}
