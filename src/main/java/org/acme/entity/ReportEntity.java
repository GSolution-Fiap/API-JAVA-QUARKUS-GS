    package org.acme.entity;

    import jakarta.persistence.*;
    import java.util.Date;

    @Entity
    @Table(name = "T_TTDLVM_REPORT")
    public class ReportEntity {
        public enum NivelGravidade {
            leve,
            moderada,
            grave
        }

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alerta_seq")
        @SequenceGenerator(name = "alerta_seq", sequenceName = "seq_tt_alerta_id", allocationSize = 1)
        @Column(name = "id_alerta")
        private Integer id;

        @Column(name = "email", nullable = false, length = 255)
        private String email;

        @Column(name = "nome", length = 100)
        private String name;

        @Column(name = "local", nullable = false, length = 100)
        private String cidade;

        @Column(name = "latitude")
        private Double latitude;

        @Column(name = "longitude")
        private Double longitude;

        @Enumerated(EnumType.STRING)
        @Column(name = "nivel_gravidade", nullable = false, length = 10)
        private NivelGravidade nivelGravidade;

        @Column(name = "data", updatable = false)
        @Temporal(TemporalType.TIMESTAMP)
        private Date data;

        @PrePersist
        protected void onCreate() {
            this.data = new Date();
        }

        public Integer getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public NivelGravidade getNivelGravidade() {
            return nivelGravidade;
        }

        public void setNivelGravidade(NivelGravidade nivelGravidade) {
            this.nivelGravidade = nivelGravidade;
        }

        public Date getData() {
            return data;
        }

        @Override
        public String toString() {
            return "ReportEntity{" +
                    "id=" + id +
                    ", email='" + email + '\'' +
                    ", name='" + name + '\'' +
                    ", cidade='" + cidade + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", nivelGravidade=" + nivelGravidade +
                    ", data=" + data +
                    '}';
        }
    }
