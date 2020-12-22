
export class CandidaturaFilter {
    constructor(
        public id?: number,
        public estadoCandidaturaNombre?: string,
        public estadoCandidaturaId?: number,
        public fuenteNombre?: string,
        public fuenteId?: number,
        public posicionTitulo?: string,
        public posicionId?: number,
        public personaDocumentoIdentidad?: string,
        public personaId?: number
    ) {

    }

    toMap(): any {

        const map = new Map();
        if (this.id != null) {
            map.set('id.equals', this.id);
        }
        
        if(this.estadoCandidaturaNombre) {
            map.set('estadoCandidaturaNombre.contains', this.estadoCandidaturaNombre)
        }

        if(this.estadoCandidaturaId!=null){
            map.set('estadoCandidatura.id', this.estadoCandidaturaId);
        }

        if(this.fuenteNombre!=null){
            map.set('fuenteNombre.contains', this.fuenteNombre);
        }

        if(this.posicionTitulo!=null){
            map.set('posicionTitulo.contains',this.posicionTitulo);
        }
        if(this.posicionId!=null){
            map.set('posicionId.equals', this.posicionId);
        }
        if(this.personaDocumentoIdentidad!=null){
            map.set('personaDocumentoIdentidad.contains', this.personaDocumentoIdentidad);
        }

        if(this.personaId!=null){
            map.set('personaId.equals', this.personaId);
        }
        return map;
    }
}