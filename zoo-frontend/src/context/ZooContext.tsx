import React, { createContext, useContext, useReducer, ReactNode } from 'react';
import { Animal } from '../types/animal';
import { Enclosure } from '../types/enclosure';

interface ZooState {
    animals: Animal[];
    enclosures: Enclosure[];
}

const initialState: ZooState = {
    animals: [],
    enclosures: [
        { id: '1', type: 'Хищники',  size: 'Большой', animalsCount: 3, capacity: 5 },
        { id: '2', type: 'Травоядные', size: 'Средний', animalsCount: 4, capacity: 6 },
        { id: '3', type: 'Птицы', size: 'Малый', animalsCount: 8, capacity: 12 },
        { id: '4', type: 'Рептилии', size: 'Средний', animalsCount: 2, capacity: 4 },
        { id: '5', type: 'Аквариум', size: 'Большой', animalsCount: 15, capacity: 20 },
        { id: '6', type: 'Насекомые', size: 'Малый', animalsCount: 30, capacity: 50 }
    ]
};

// действия

type Action =
    | { type: 'ADD_ANIMAL'; payload: Animal }
    | { type: 'ADD_ENCLOSURE'; payload: Enclosure }
    | { type: 'FEED_ENCLOSURE'; payload: { id: string } }
    | { type: 'TREAT_ENCLOSURE'; payload: { id: string } }
    | { type: 'MOVE_ANIMAL'; payload: { animalId: string; targetEnclosureId: string } };

function reducer(state: ZooState, action: Action): ZooState {
    switch (action.type) {
        case 'ADD_ANIMAL':
            return { ...state, animals: [...state.animals, action.payload] };
        case 'ADD_ENCLOSURE':
            return { ...state, enclosures: [...state.enclosures, action.payload] };
        case 'FEED_ENCLOSURE':
            // пока что просто всплывающее окно; логика может быть сложнее
            alert(`Вольер ${action.payload.id} накормлен!`);
            return state;
        case 'TREAT_ENCLOSURE':
            alert(`Животные в вольере ${action.payload.id} пролечены!`);
            return state;
        case 'MOVE_ANIMAL': {
            const { animalId, targetEnclosureId } = action.payload;
            return {
                ...state,
                animals: state.animals.map(a =>
                    a.id === animalId ? { ...a, enclosureId: targetEnclosureId } : a
                )
            };
        }
        default:
            return state;
    }
}

const ZooContext = createContext<{
    state: ZooState;
    dispatch: React.Dispatch<Action>;
}>({ state: initialState, dispatch: () => {} });

export const useZoo = () => useContext(ZooContext);

export function ZooProvider({ children }: { children: ReactNode }) {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <ZooContext.Provider value={{ state, dispatch }}>
            {children}
        </ZooContext.Provider>
    );
}