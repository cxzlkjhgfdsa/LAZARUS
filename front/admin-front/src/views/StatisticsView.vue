<template>
    <div class="d-flex flex-column align-items-center ranking_back">
        <div class="main d-flex flex-column align-items-center justify-content-start">
            <div>
                <span class="title_font">📊STATISTICS📊</span>
            </div>

            <b-container
                class="d-flex flex-column align-items-center justify-content-center container_style"
            >
                <b-row class="row_size">
                    <b-col class="d-flex flex-column col_shape mx-5 align-items-center">
                        <div class="ranking_title d-flex align-items-center justify-content-center">
                            <span class="ranking_title_font"> 조합 아이템 제작 </span>
                        </div>
                        <canvas ref="craftChart" width="500" height="300" class="mt-3"></canvas>
                    </b-col>
                    <b-col class="d-flex flex-column col_shape mx-5 align-items-center">
                        <div class="ranking_title d-flex align-items-center justify-content-center">
                            <span class="ranking_title_font"> 음식 아이템 소비 </span>
                        </div>
                        <canvas ref="eatChart" width="500" height="300" class="mt-3"></canvas>
                    </b-col>
                </b-row>
                <b-row class="row_size">
                    <b-col class="d-flex flex-column col_shape mx-5 align-items-center">
                        <div class="ranking_title d-flex align-items-center justify-content-center">
                            <span class="ranking_title_font"> 많이 사냥된 몬스터 </span>
                        </div>
                        <canvas ref="monsterChart" width="500" height="300" class="mt-3"></canvas>
                    </b-col>
                    <b-col class="d-flex flex-column col_shape mx-5 align-items-center">
                        <div class="ranking_title d-flex align-items-center justify-content-center">
                            <span class="ranking_title_font"> 최근 10게임 탈출 시간</span>
                        </div>
                        <canvas ref="escapeChart" width="500" height="300" class="mt-3"></canvas>
                    </b-col>
                </b-row>
            </b-container>
        </div>
    </div>
</template>

<script>
import Chart from "chart.js/auto";
import axios from "axios";
import moment from "moment";

export default {
    data() {
        return {
            craftItemData: [],
            usedItemData: [],
            huntMonsterData: [],
            whenLabelData: [],
            spentTimeList: [],
        };
    },
    mounted() {
        axios
            .get(process.env.VUE_APP_SERVER_URL + "/search/statistics")
            .then((res) => {
                console.log(res.data);
                this.craftItemData = res.data.craftItemList;
                this.usedItemData = res.data.useFoodList;
                this.whenLabelData = res.data.whenList;
                this.spentTimeList = res.data.spentTimeList;
                this.huntMonsterData = res.data.huntedMonsterList;

                this.renderCraftChart();
                this.renderEatChart();
                this.renderMonsterChart();
                this.renderEscapeChart();
            })
            .catch((error) => {
                console.log(error);
            });
    },

    methods: {
        renderCraftChart() {
            console.log("data == " + this.craftItemData);
            const ctx = this.$refs.craftChart.getContext("2d");
            new Chart(ctx, {
                type: "bar",
                data: {
                    labels: [
                        "화살",
                        "모닥불",
                        "조잡한곡괭이",
                        "조잡한도끼",
                        "조잡합활",
                        "단단한곡괭이",
                        "단단한도끼",
                        "단단한활",
                    ],
                    datasets: [
                        {
                            data: this.craftItemData,
                            backgroundColor: [
                                "#FA5858",
                                "#F3E2A9",
                                "#E1F5A9",
                                "#A9F5D0",
                                "#81BEF7",
                                "#F5A9F2",
                                "#F5A9BC",
                                "#58FAD0",
                            ],
                        },
                    ],
                },
                options: {
                    plugins: {
                        legend: {
                            display: false,
                        },
                    },
                    responsive: false,
                    hover: {
                        mode: "dataset",
                    },
                    scales: {
                        y: {
                            beginAtZero: true,
                        },
                    },
                    // 차트 옵션 설정
                },
            });
        },
        renderEatChart() {
            const ctx = this.$refs.eatChart.getContext("2d");
            new Chart(ctx, {
                type: "bar",
                data: {
                    labels: [
                        "식용버섯",
                        "맛있는독버섯",
                        "향료버섯",
                        "질긴고기",
                        "맛좋은고기",
                        "고단백고기",
                        "구은 질긴고기",
                        "구운 맛좋은고기",
                        "구운 고단백고기",
                        "버섯 탕수육",
                        "버섯 샤브샤브",
                        "송이버섯 고깃국",
                    ],
                    datasets: [
                        {
                            data: this.usedItemData,
                            backgroundColor: [
                                "#FA5858",
                                "#F3E2A9",
                                "#E1F5A9",
                                "#A9F5D0",
                                "#81BEF7",
                                "#F5A9F2",
                                "#F5A9BC",
                                "#58FAD0",
                                "#0B610B",
                                "#3B170B",
                                "#8A084B",
                                "#BDBDBD",
                            ],
                        },
                    ],
                },
                options: {
                    plugins: {
                        legend: {
                            display: false,
                        },
                    },
                    responsive: false,
                    hover: {
                        mode: "dataset",
                    },
                    scales: {
                        y: {
                            beginAtZero: true,
                        },
                    },
                    // 차트 옵션 설정
                },
            });
        },
        renderMonsterChart() {
            const ctx = this.$refs.monsterChart.getContext("2d");
            new Chart(ctx, {
                type: "bar",
                data: {
                    labels: [
                        "암사슴",
                        "보스암사슴",
                        "숫사슴",
                        "보스숫사슴",
                        "멧돼지",
                        "늑대",
                        "보스늑대",
                        "곰",
                        "보스곰",
                        "좀비숫사슴",
                        "좀비늑대",
                        "좀비보스곰",
                        "좀비무스",
                    ],
                    datasets: [
                        {
                            data: this.huntMonsterData,
                            backgroundColor: [
                                "#FA5858",
                                "#F3E2A9",
                                "#E1F5A9",
                                "#A9F5D0",
                                "#81BEF7",
                                "#F5A9F2",
                                "#F5A9BC",
                                "#58FAD0",
                                "#0B610B",
                                "#3B170B",
                                "#8A084B",
                                "#BDBDBD",
                                "#BCA9F5",
                            ],
                        },
                    ],
                },
                options: {
                    plugins: {
                        legend: {
                            display: false,
                        },
                    },
                    responsive: false,
                    hover: {
                        mode: "dataset",
                    },
                    scales: {
                        y: {
                            beginAtZero: true,
                        },
                    },
                    // 차트 옵션 설정
                },
            });
        },
        renderEscapeChart() {
            const ctx = this.$refs.escapeChart.getContext("2d");
            new Chart(ctx, {
                type: "bar",
                data: {
                    labels: this.whenLabelData,
                    datasets: [
                        {
                            data: this.spentTimeList,
                            backgroundColor: [
                                "#FA5858",
                                "#F3E2A9",
                                "#E1F5A9",
                                "#A9F5D0",
                                "#81BEF7",
                                "#F5A9F2",
                                "#F5A9BC",
                                "#58FAD0",
                                "#0B610B",
                                "#3B170B",
                            ],
                        },
                    ],
                },
                options: {
                    plugins: {
                        legend: {
                            display: false,
                        },
                    },
                    responsive: false,
                    hover: {
                        mode: "dataset",
                    },
                    scales: {
                        y: {
                            beginAtZero: true,
                            ticks: {
                                callback: function (value, index, values) {
                                    console.log(index, values);
                                    return moment.utc(value * 1000).format("HH:mm:ss");
                                },
                            },
                        },
                    },
                    // 차트 옵션 설정
                },
            });
        },
    },
};
</script>

<style scoped>
.ranking_back {
    background: linear-gradient(to bottom, #fbfbef, #2efec8);
    height: 1200px;
}
.main {
    width: 1600px;
    height: 100%;
}
.title_font {
    font-size: 5em;
    font-weight: bold;
    /*
    background: linear-gradient(to bottom, #f6cece, #d358f7);
    -webkit-background-clip: text;
    color: transparent;
    */
    /*color: #04b486; */
    color: #0b3b17;
}
.row_size {
    width: 1400px;
    height: 400px;
    margin-bottom: 50px;
}
.container_style {
    margin-top: 50px;
}
.col_shape {
    border-radius: 60px;
    background-color: white;
    height: 100%;
}
.ranking_title {
    width: 100%;
    height: 20%;
    border-bottom: 5px solid #e6e6e6;
}
.ranking_body_font {
    font-size: 1.5em;

    margin-left: 15px;
}
.ranking_title_font {
    font-size: 2em;

    color: #04b486;
}
</style>
