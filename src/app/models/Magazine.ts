import { SciField } from './SciField';

export class Magazine {
    id: string;
    description: string;
    isopenaccess: boolean;
    title: string;
    sciFields: SciField[];
}