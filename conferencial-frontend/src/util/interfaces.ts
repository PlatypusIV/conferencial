export interface Room {
    id: number;
    name: string;
    location: string;
    maxSeats: number;
}

export interface Conference {
    id: number;
    name: string;
    roomId: number;
    startTime: string;
    endTime: string;
    canceled?: boolean;
}

export interface Participant {
    conferenceId: number;
    fullName: string;
    birthDate: string;
}